package ru.osokin.portalfbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FBIPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FBIPortalApplication.class, args);
	}

}
