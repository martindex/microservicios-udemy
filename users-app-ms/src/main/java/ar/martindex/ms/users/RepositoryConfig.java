package ar.martindex.ms.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import ar.martindex.ms.commons.models.entities.Role;
import ar.martindex.ms.commons.models.entities.UserApp;


@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(UserApp.class, Role.class);
	}
}
