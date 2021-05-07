package com.formacionbdi.springboot.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioUsersAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServicioUsersAppApplication.class, args);
    }

}
