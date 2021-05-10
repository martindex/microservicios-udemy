package ar.martindex.ms.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UsersAppMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersAppMsApplication.class, args);
    }

}
