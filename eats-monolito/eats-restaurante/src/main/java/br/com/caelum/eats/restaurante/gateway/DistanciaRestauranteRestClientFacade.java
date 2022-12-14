package br.com.caelum.eats.restaurante.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;



@Slf4j
@Service
@AllArgsConstructor
public class DistanciaRestauranteRestClientFacade {
	
	@Autowired
	WebClient webClient;

	public void criaDistanciaRestaurante(RestauranteRequest request) {
		
		Mono<Restaurante> mono =  this.webClient
				.post()
				.uri("/restaurantes")
				.body(Mono.just(request), RestauranteRequest.class)
				.retrieve()
				.onStatus(HttpStatus::is1xxInformational,
						error -> Mono.error(new RuntimeException("Erro 1xx")))
				.onStatus(HttpStatus::is3xxRedirection,
						error -> Mono.error(new RuntimeException("Erro 3xx")))
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Erro 4xx")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new RuntimeException("Erro 5xx")))
				.bodyToMono(Restaurante.class);
		
		mono.block();	
		mono.subscribe(p->{
			log.info("Restaurante adicionado com sucesso no serviço de distancia - " + p.getId());
			//System.out.print("Restaurante adicionado com sucesso no serviço de distancia - "+p.getId());
		});
		
		
//		AsyncRestTemplate restTemplate = new AsyncRestTemplate();
//	    String baseUrl = "http://distancia-service:8082";
//	    HttpHeaders requestHeaders = new HttpHeaders();
//	    requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//	    HttpEntity entity = new HttpEntity("parameters", requestHeaders);
//	    final DeferredResult<String> result = new DeferredResult<>();
//	    ListenableFuture<ResponseEntity<Restaurante>> futureEntity = restTemplate.getForEntity(baseUrl, Restaurante.class);
//
//	    futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<Restaurante>>() {
//	        @Override
//	        public void onSuccess(ResponseEntity<Restaurante> result) {
//	        	log.info("restuarante inserido com sucesso " + result.getBody().getId());
//	        }
//
//	        @Override
//	        public void onFailure(Throwable ex) {
//	            result.setErrorResult(ex.getMessage());
//	            log.error("Erro ao chamar API para grava��o de dist�ncia do restaurante.", ex);
//				throw new RuntimeException("Problema ao tentar cadastrar dist�ncia do restaurante. restauranteId: " + request.getId());
//	        }
//	    });
	}   
	
	public void atualizaDistanciaRestaurante(RestauranteRequest request) {
		   
		Mono<Restaurante> mono =  this.webClient
				.put()
				.uri("/restaurantes/"+request.getId())
				.body(Mono.just(request), RestauranteRequest.class)
				.retrieve()
				.onStatus(HttpStatus::is1xxInformational,
						error -> Mono.error(new RuntimeException("Erro 1xx")))
				.onStatus(HttpStatus::is3xxRedirection,
						error -> Mono.error(new RuntimeException("Erro 3xx")))
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Erro 4xx")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new RuntimeException("Erro 5xx")))
				.bodyToMono(Restaurante.class);
		
//		mono.block();	
		mono.subscribe(p->{
			log.info("Restaurante atualizado com sucesso no serviço de distancia - "+p.getId());
			//System.out.print("Restaurante atualizado com sucesso no serviço de distancia - "+p.getId());
		});
		
        /*try {
        	//this.restClient.atualizaDistanciaRestaurante(BigInteger.valueOf(request.getId()), request);
		} catch (FeignException e) {
			log.error("Erro ao chamar API para atualiza��o de dist�ncia do restaurante.", e);
			throw new RuntimeException("Problema ao tentar alterar dist�ncia do restaurante. restauranteId: " + request.getId());
		}*/
        
    }

}