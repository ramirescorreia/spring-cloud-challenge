package br.com.caelum.eats.restaurante.gateway;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.caelum.eats.restaurante.gateway.domain.RestauranteRequest;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
@AllArgsConstructor
public class DistanciaRestauranteRestClientFacade {
	

	public void criaDistanciaRestaurante(RestauranteRequest request) {
		AsyncRestTemplate restTemplate = new AsyncRestTemplate();
	    String baseUrl = "http://distancia-service:8082";
	    HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    HttpEntity entity = new HttpEntity("parameters", requestHeaders);
	    final DeferredResult<String> result = new DeferredResult<>();
	    ListenableFuture<ResponseEntity<Restaurante>> futureEntity = restTemplate.getForEntity(baseUrl, Restaurante.class);

	    futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<Restaurante>>() {
	        @Override
	        public void onSuccess(ResponseEntity<Restaurante> result) {
	        	log.info("restuarante inserido com sucesso " + result.getBody().getId());
	        }

	        @Override
	        public void onFailure(Throwable ex) {
	            result.setErrorResult(ex.getMessage());
	            log.error("Erro ao chamar API para grava��o de dist�ncia do restaurante.", ex);
				throw new RuntimeException("Problema ao tentar cadastrar dist�ncia do restaurante. restauranteId: " + request.getId());
	        }
	    });
	}   
	
	public void atualizaDistanciaRestaurante(RestauranteRequest request) {
		   
        /*try {
        	//this.restClient.atualizaDistanciaRestaurante(BigInteger.valueOf(request.getId()), request);
		} catch (FeignException e) {
			log.error("Erro ao chamar API para atualiza��o de dist�ncia do restaurante.", e);
			throw new RuntimeException("Problema ao tentar alterar dist�ncia do restaurante. restauranteId: " + request.getId());
		}*/
        
    }

}