package br.com.caelum.eats.restaurante.gateway;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;

@FeignClient(name="distancia", url="http://distancia-service:8082")
public interface DistanciaRestauranteFeignRestClient {

	@PostMapping("/restaurantes")
	void criaDistanciaRestaurante(RestauranteRequest request);
	
	@PutMapping("/restaurantes/{restauranteId}")
	void atualizaDistanciaRestaurante(@PathVariable("restauranteId") BigInteger restauranteId, RestauranteRequest request);
	
}
