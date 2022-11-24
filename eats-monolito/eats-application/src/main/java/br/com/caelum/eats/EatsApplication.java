package br.com.caelum.eats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.nativex.hint.TypeHint;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.configuration.LoggingIntializer;
import br.com.caelum.eats.restaurante.gateway.DistanciaRestauranteFeignRestClient;
import br.com.caelum.eats.restaurante.gateway.DistanciaRestauranteRestClientFacade;

@EnableFeignClients
@SpringBootApplication
@TypeHint(types = { Application.class, InstanceInfo.class, MyDataCenterInfo.class }, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
})
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EatsApplication.class, args);
	}

}
