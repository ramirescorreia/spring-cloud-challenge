package br.com.caelum.eats;

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.HttpClient5DisabledConditions;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.restaurante.config.FeignConfig;
import br.com.caelum.eats.restaurante.gateway.DistanciaRestauranteFeignRestClient;

@JdkProxyHint(types = {PathVariable.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestHeader.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestBody.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestParam.class, SynthesizedAnnotation.class})
@AotProxyHint(targetClass=br.com.caelum.eats.configuration.AppConfiguration.class, interfaces={org.springframework.aop.scope.ScopedObject.class, 
		                 java.io.Serializable.class, org.springframework.aop.framework.AopInfrastructureBean.class})
@TypeHint(types = {FeignConfig.class, FeignClient.class, FeignClientsConfiguration.class, Application.class, InstanceInfo.class, MyDataCenterInfo.class,
				   DistanciaRestauranteFeignRestClient.class, HttpClient5DisabledConditions.class, ScopedProxyFactoryBean.class}, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
		"org.springframework.aop.scope",
		"org.springframework.cloud.openfeign",
		"br.com.caelum.eats.configuration",
		"br.com.caelum.eats.restaurante"
})
@EnableFeignClients(defaultConfiguration= {FeignConfig.class}, basePackages= {"br.com.caelum.eats"}, clients = DistanciaRestauranteFeignRestClient.class)
@SpringBootApplication(scanBasePackages = "br.com.caelum.eats")
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EatsApplication.class, args);
	}

}
