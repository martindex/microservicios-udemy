package ar.martindex.ms.oauth.servicies;

import ar.martindex.ms.commons.models.entities.UserApp;

public interface UserAppService {
    UserApp findByUsername(String username);
}
