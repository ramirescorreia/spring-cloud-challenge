package br.com.caelum.eats.pagamento;

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeAccess;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClient;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.pagamento.config.LoggingIntializer;
import br.com.caelum.eats.pagamento.gateway.domain.PedidoDto;
import br.com.caelum.eats.pagamento.gateway.domain.PedidoMudancaDeStatusRequest;


@JdkProxyHint(types = {PathVariable.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestHeader.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestBody.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {RequestParam.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {ResponseStatus.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {PostMapping.class, SynthesizedAnnotation.class})
@JdkProxyHint(types = {PutMapping.class, SynthesizedAnnotation.class})

@TypeHint(types = {Application.class, InstanceInfo.class, MyDataCenterInfo.class, ScopedProxyFactoryBean.class}, typeNames = {
	"com.netflix.discove,ry.shared.Application",
	"com.netflix.appinfo.InstanceInfo",
	"com.netflix.appinfo.InstanceInfo$PortWrapper",
	"com.netflix.appinfo.DataCenterInfo",
	"org.springframework.aop.scope",
	"org.springframework.web.bind.annotation",
	"org.springframework.core.annotation",
	"org.springframework.cloud.openfeign",
	"brave.kafka.clients.TracingProducer",
	"brave.kafka.clients.TracingConsumer"
})
@TypeHint(types = PedidoDto.class, access = { TypeAccess.DECLARED_CONSTRUCTORS, TypeAccess.PUBLIC_METHODS })
@TypeHint(types = PedidoMudancaDeStatusRequest.class, access = { TypeAccess.DECLARED_CONSTRUCTORS, TypeAccess.PUBLIC_METHODS })
@SpringBootApplication
public class EatsPagamentoServiceApplication {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
				.baseUrl("http://application:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsPagamentoServiceApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
