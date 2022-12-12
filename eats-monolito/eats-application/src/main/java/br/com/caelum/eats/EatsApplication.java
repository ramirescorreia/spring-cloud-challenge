package br.com.caelum.eats;

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.configuration.AppConfiguration;
import br.com.caelum.eats.configuration.LoggingIntializer;
import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;


@JdkProxyHint(types = {PathVariable.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestHeader.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestBody.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestParam.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {ResponseStatus.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {PostMapping.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {PutMapping.class, SynthesizedAnnotation.class})

@AotProxyHint(targetClass=AppConfiguration.class, interfaces={org.springframework.aop.scope.ScopedObject.class, 
        java.io.Serializable.class, org.springframework.aop.framework.AopInfrastructureBean.class})

@TypeHint(types = {Application.class, InstanceInfo.class, MyDataCenterInfo.class, ScopedProxyFactoryBean.class, RestauranteRequest.class, 
		MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class}, typeNames = {
	"com.netflix.discove,ry.shared.Application",
	"com.netflix.appinfo.InstanceInfo",
	"com.netflix.appinfo.InstanceInfo$PortWrapper",
	"com.netflix.appinfo.DataCenterInfo",
	"org.springframework.aop.scope",
	"org.springframework.web.bind.annotation",
	"org.springframework.core.annotation",
	"org.springframework.boot.autoconfigure.condition",
	"org.springframework.http.converter",
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
