package br.com.caelum.eats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EatsApplication.class, args); 
	}

}
