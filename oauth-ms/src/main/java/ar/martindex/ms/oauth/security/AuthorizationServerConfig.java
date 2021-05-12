package ar.martindex.ms.oauth.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@RefreshScope
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final InfoToken infoToken;

    private final String clientId;
    private final String clientSecret;
    private final String jwtKey;

    @Autowired
    public AuthorizationServerConfig(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AuthenticationManager authenticationManager,
            InfoToken infoToken,
            @Value("${config.security.oauth.client.id}") String clientId,
            @Value("${config.security.oauth.client.secret}") String clientSecret,
            @Value("${config.security.oauth.jwt.key}") String jwtKey) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.infoToken = infoToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.jwtKey = jwtKey;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(bCryptPasswordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
                //.and().withClient()... for others clients

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //Para enlazar la informacion adicional hacemos lo siguiente
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(
                infoToken, // Informacion Adicional
                buildAccessTokenConverter() // Info por defecto
        ));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(buildTokenStore())
                .accessTokenConverter(buildAccessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain); // agregamos al token la info adicionl mas la por defecto
    }

    @Bean
    public JwtTokenStore buildTokenStore() {
        return new JwtTokenStore(buildAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter buildAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(jwtKey);
        return jwtAccessTokenConverter;
    }
}
