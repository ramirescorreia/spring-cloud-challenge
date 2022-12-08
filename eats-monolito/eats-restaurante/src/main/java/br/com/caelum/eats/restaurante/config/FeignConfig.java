package br.com.caelum.eats.restaurante.config;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;


@Configuration
@LoadBalancerClient(value = "distRestService", configuration = CustomLoadBalancerConfiguration.class)
public class FeignConfig {

	@Bean
    public Logger.Level getLoggerLevel() {
        return Logger.Level.FULL;
    }
	
}
