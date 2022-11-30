package br.com.caelum.eats.restaurante.gateway;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient("eats-distancia-service")
public interface DistanciaRestauranteFeignRestClient {


	@RequestLine(value = "POST /restaurantes")
	@Headers("Content-Type: application/json")
	void criaDistanciaRestaurante(RestauranteRequest request);
	
	@RequestLine(value= "PUT /restaurantes/{restauranteId}")
	@Headers("Content-Type: application/json")
	void atualizaDistanciaRestaurante(@Param("restauranteId") BigInteger restauranteId, RestauranteRequest request);
	
}
