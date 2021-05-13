package ar.martindex.ms.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ar.martindex.ms.commons.models.entities.UserApp;

@FeignClient(name = "users-app-ms")
@RequestMapping("/users-app")
public interface UserAppFeignClient {
    @GetMapping("/search/find-by-username")
    UserApp findByUsername(@RequestParam("user") String username);

    @PutMapping("{id}")
    UserApp update(@RequestBody UserApp userApp, @PathVariable Long id);
}
