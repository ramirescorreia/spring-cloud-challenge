package br.com.caelum.eats.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.nativex.hint.TypeHint;

@SpringBootApplication
@EnableEurekaServer

public class EatsServiceRegistryApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EatsServiceRegistryApplication.class, args);
	}

}
