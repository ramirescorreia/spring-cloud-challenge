package br.com.caelum.eats.pagamento;

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.HttpClient5DisabledConditions;
import org.springframework.nativex.hint.TypeHint;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.pagamento.config.FeignConfig;

@TypeHint(types = {Application.class, InstanceInfo.class, MyDataCenterInfo.class, HttpClient5DisabledConditions.class, ScopedProxyFactoryBean.class, 
		FeignConfig.class, FeignClientsConfiguration.class}, typeNames = {
	"com.netflix.discove,ry.shared.Application",
	"com.netflix.appinfo.InstanceInfo",
	"com.netflix.appinfo.InstanceInfo$PortWrapper",
	"com.netflix.appinfo.DataCenterInfo",
	"org.springframework.aop.scope",
	"org.springframework.cloud.openfeign",
	"brave.kafka.clients.TracingProducer",
	"brave.kafka.clients.TracingConsumer",
})
@SpringBootApplication
public class EatsPagamentoServiceApplication {

	public static void main(String[] args) {
		//new SpringApplicationBuilder(EatsPagamentoServiceApplication.class).initializers(new LoggingIntializer()).run(args);
		SpringApplication.run(EatsPagamentoServiceApplication.class, args);
	}

}
