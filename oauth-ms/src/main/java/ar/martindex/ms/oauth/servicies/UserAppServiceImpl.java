package ar.martindex.ms.oauth.servicies;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ar.martindex.ms.commons.models.entities.UserApp;
import ar.martindex.ms.oauth.clients.UserAppFeignClient;
import feign.FeignException;

@Service
public class UserAppServiceImpl implements UserAppService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserAppServiceImpl.class);

    private final UserAppFeignClient userAppFeignClient;

    @Autowired
    public UserAppServiceImpl(UserAppFeignClient userAppFeignClient) {
        this.userAppFeignClient = userAppFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserApp userApp = userAppFeignClient.findByUsername(username);
            List<GrantedAuthority> authorities = userApp.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek(authority -> logger.info("role: "+ authority.getAuthority()))
                    .collect(Collectors.toList());

            logger.info("User authenticated: " + username);
            return buildUserSpringBootSecurity(userApp, authorities);
        } catch (FeignException e) {
            logger.error("user not exist: {}", username);
            throw new UsernameNotFoundException("user not exist " + username);
        }

    }

    public User buildUserSpringBootSecurity(UserApp userApp, List<GrantedAuthority> authorities){
        return new User(
                userApp.getUsername(),
                userApp.getPassword(),
                userApp.getEnabled(),
                true,
                true,
                true,
                authorities
                );
    }

    @Override
    public UserApp findByUsername(String username) {
        return userAppFeignClient.findByUsername(username);
    }

    @Override
    public UserApp update(UserApp userApp, Long id) {
        return userAppFeignClient.update(userApp, id);
    }
}
