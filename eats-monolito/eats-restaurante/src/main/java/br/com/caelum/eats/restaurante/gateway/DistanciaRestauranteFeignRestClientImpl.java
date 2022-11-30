package br.com.caelum.eats.restaurante.gateway;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Component
@Import(FeignClientsConfiguration.class)
public class DistanciaRestauranteFeignRestClientImpl {

	private DistanciaRestauranteFeignRestClient feignRestClientInterface;
	
	@Autowired
	public DistanciaRestauranteFeignRestClientImpl(Decoder decoder, Encoder encoder, Client client) {
		this.feignRestClientInterface = Feign.builder().client(client).encoder(encoder).decoder(decoder)
				.target(DistanciaRestauranteFeignRestClient.class, "http://distancia-service:8082");
	}
	
	@PostMapping("/restaurantes")
	public void criaDistanciaRestaurante(RestauranteRequest request) {
		this.feignRestClientInterface.criaDistanciaRestaurante(request);
	}
	
	@PutMapping("/restaurantes/{restauranteId}")
	void atualizaDistanciaRestaurante(@PathVariable("restauranteId") BigInteger restauranteId, RestauranteRequest request) {
		this.feignRestClientInterface.atualizaDistanciaRestaurante(restauranteId, request);
	}
	
}