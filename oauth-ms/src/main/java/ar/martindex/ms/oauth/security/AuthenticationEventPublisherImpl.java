package ar.martindex.ms.oauth.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventPublisherImpl implements AuthenticationEventPublisher {

    private Logger logger = LoggerFactory.getLogger(AuthenticationEventPublisherImpl.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("Success login: "+ userDetails.getUsername());
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        logger.error("Error login: "+ exception.getMessage());
    }
}
