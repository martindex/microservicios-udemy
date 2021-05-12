package ar.martindex.ms.oauth.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import ar.martindex.ms.commons.models.entities.UserApp;
import ar.martindex.ms.oauth.servicies.UserAppService;

/*
* Una clase para info adicional en el token
* */

@Component
public class InfoToken implements TokenEnhancer {

    private final UserAppService userAppService;

    @Autowired
    public InfoToken(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();
        UserApp userApp = userAppService.findByUsername(oAuth2Authentication.getName());
        info.put("name", userApp.getName());
        info.put("lastname", userApp.getLastName());
        info.put("email", userApp.getEmail());
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
