package br.com.caelum.eats.pagamento;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.caelum.eats.pagamento.config.LoggingIntializer;



@EnableFeignClients
@SpringBootApplication
public class EatsPagamentoServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsPagamentoServiceApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
