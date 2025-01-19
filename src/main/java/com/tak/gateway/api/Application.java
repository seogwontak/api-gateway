package com.tak.gateway.api;

import com.tak.gateway.api.domain.config.properties.LoadAdditionalProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.tak"})
public class Application {

	public static void main(String[] args) {
		// Set default profile if not provided
		String profile = System.getProperty("spring.profiles.active", "local");
		System.setProperty("spring.profiles.active", profile);

		//Set custome application property
		System.setProperty("app.name", "api-gateway");

		new SpringApplicationBuilder(Application.class)
				.listeners(new LoadAdditionalProperties())
				.run(args);
	}

}
