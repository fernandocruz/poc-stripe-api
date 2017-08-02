package com.ebanx.init;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig(){
		this.packages("com.ebanx.controller");
	}

}
