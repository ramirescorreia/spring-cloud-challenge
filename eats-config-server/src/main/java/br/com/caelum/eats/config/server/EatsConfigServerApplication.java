package br.com.caelum.eats.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableConfigServer
public class EatsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatsConfigServerApplication.class, args);
	}

}
