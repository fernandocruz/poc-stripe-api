package com.ebanx.init;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ebanx")
public class Application extends SpringBootServletInitializer {
	
	private static Class<Application> applicationClass = Application.class;
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		new Application().configure(
				new SpringApplicationBuilder(applicationClass)).run(args);
	}

}
