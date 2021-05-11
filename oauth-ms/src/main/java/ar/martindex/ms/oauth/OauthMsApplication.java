package ar.martindex.ms.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ar.martindex.ms.oauth.servicies.UserAppService;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"ar.martindex.ms.commons.models.entities"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class OauthMsApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(OauthMsApplication.class);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public OauthMsApplication(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(OauthMsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "123456";
        for (int i = 0; i < 4; i++) {
            String encriptedPassword = bCryptPasswordEncoder.encode(password);
            logger.info("pass: "+ encriptedPassword);
        }
    }
}
