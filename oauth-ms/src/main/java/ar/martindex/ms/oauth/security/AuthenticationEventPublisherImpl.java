package ar.martindex.ms.oauth.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ar.martindex.ms.commons.models.entities.UserApp;
import ar.martindex.ms.oauth.servicies.UserAppService;
import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationEventPublisherImpl implements AuthenticationEventPublisher {

    private Logger logger = LoggerFactory.getLogger(AuthenticationEventPublisherImpl.class);

    private final UserAppService userAppService;
    private final Tracer tracer;

    @Autowired
    public AuthenticationEventPublisherImpl(UserAppService userAppService, Tracer tracer) {
        this.userAppService = userAppService;
        this.tracer = tracer;
    }

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        try {
            UserApp userApp = userAppService.findByUsername(authentication.getName());
            userApp.setIntents(0);
            userAppService.update(userApp, userApp.getId());
            logger.info("Success login: {}", authentication.getName() );
        } catch (FeignException e){
            logger.error("User not found or user is client id: {}", authentication.getName());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

        StringBuilder buildError = new StringBuilder();

        logger.error("Error login: " + exception.getMessage());
        buildError.append("Error login: " + exception.getMessage());
        try {
            UserApp userApp = userAppService.findByUsername(authentication.getName());
            if(!userApp.getEnabled()){
                logger.error("Disabled user: {}", authentication.getName());
                buildError.append("Disabled user: " + authentication.getName());
                tracer.currentSpan().tag("error.message",buildError.toString());
                return;
            }
            userApp.setIntents(userApp.getIntents()+1);
            logger.info("Intents count: {}", userApp.getIntents());
            buildError.append("Intents count: " + userApp.getIntents());
            if(userApp.getIntents()>=3){
                userApp.setEnabled(false);
            }
            userAppService.update(userApp, userApp.getId());
            tracer.currentSpan().tag("error.message",buildError.toString());
        } catch (FeignException e){
            logger.error("Error feign: {}", e.getMessage());
            tracer.currentSpan().tag("Error feign: {}", e.getMessage());
        }
    }
}
