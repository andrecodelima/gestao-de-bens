package br.net.gest.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.net.gest.sys.entity")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.net.gest.sys.repository"})
public class GestaoBensApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoBensApplication.class, args);
	}

}
