package ar.martindex.ms.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"ar.martindex.ms.commons.models.entities"})
public class ProductosMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosMsApplication.class, args);
	}

}
