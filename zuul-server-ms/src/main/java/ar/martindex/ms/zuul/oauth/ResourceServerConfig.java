package ar.martindex.ms.zuul.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@RefreshScope
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String jwtKey;

    public ResourceServerConfig(@Value("${config.security.oauth.jwt.key}") String jwtKey) {
        this.jwtKey = jwtKey;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(buildTokenStore()); //protegemos el token
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/oauth-ms/oauth/token")
                .permitAll() // publica la url de autenticacion para cualquiera
                .antMatchers(HttpMethod.GET,
                        "/api/productos-ms/producto/listar",
                        "/api/items-ms/item/listar",
                        "/api/users-app-ms/users-app")
                .permitAll() // publicos los GET de mi aplicacion
                .antMatchers(HttpMethod.GET,
                        "/api/productos-ms/producto/ver/{id}",
                        "/api/items-ms/item/ver/{id}/cantidad/{cantidad}",
                        "/api/users-app-ms/users-app/{id}")
                .hasAnyRole("ADMIN", "USER") // estos gets son para administrador y usuario
                .antMatchers("/api/productos-ms/**",
                        "/api/items-ms/**",
                        "/api/users-app-ms/**")
                .hasRole("ADMIN") // todos los endpoints restantes que no estan especificados anteriormente solo lo pueden usar el administrador
                .anyRequest().authenticated(); // para cualquier otra ruta, autenticar
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
