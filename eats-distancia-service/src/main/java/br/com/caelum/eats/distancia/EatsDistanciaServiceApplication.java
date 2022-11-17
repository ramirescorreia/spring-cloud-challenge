package br.com.caelum.eats.distancia;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class EatsDistanciaServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsDistanciaServiceApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
