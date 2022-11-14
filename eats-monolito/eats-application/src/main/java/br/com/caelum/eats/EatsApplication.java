package br.com.caelum.eats;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.caelum.eats.configuration.LoggingIntializer;

@EnableFeignClients
@SpringBootApplication
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
