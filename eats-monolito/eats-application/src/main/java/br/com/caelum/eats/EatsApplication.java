package br.com.caelum.eats;

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.HttpClient5DisabledConditions;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.TypeHint;

import br.com.caelum.eats.configuration.LoggingIntializer;
import br.com.caelum.eats.restaurante.config.FeignConfig;


@AotProxyHint(targetClass=br.com.caelum.eats.configuration.AppConfiguration.class, interfaces={org.springframework.aop.scope.ScopedObject.class, 
		                 java.io.Serializable.class, org.springframework.aop.framework.AopInfrastructureBean.class})
@TypeHint(types = {HttpClient5DisabledConditions.class, ScopedProxyFactoryBean.class, FeignConfig.class, FeignClientsConfiguration.class}, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
		"org.springframework.aop.scope",
		"org.springframework.cloud.openfeign",
		"brave.kafka.clients.TracingProducer",
		"brave.kafka.clients.TracingConsumer",
		"br.com.caelum.eats.configuration",
		"br.com.caelum.eats.restaurante"
})

@SpringBootApplication
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
