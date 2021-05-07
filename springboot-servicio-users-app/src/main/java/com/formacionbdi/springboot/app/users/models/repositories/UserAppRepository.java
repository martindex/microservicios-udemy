package com.formacionbdi.springboot.app.users.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.formacionbdi.springboot.app.users.models.entities.UserApp;

@RepositoryRestResource(path = "usersapp")
public interface UserAppRepository extends PagingAndSortingRepository<UserApp, Long> {
    @RestResource(path = "find-by-username")
    public UserApp findByUsername(@Param("user") String username);
}
