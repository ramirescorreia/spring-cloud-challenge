package br.com.caelum.eats.restaurante.gateway;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
	

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void criaDistanciaRestaurante(RestauranteRequest request) {
		AsyncRestTemplate restTemplate = new AsyncRestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
	    HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    HttpEntity entity = new HttpEntity(request, requestHeaders);
	    final DeferredResult<String> result = new DeferredResult<>();
	    String url = "http://distancia-service:8082/restaurantes";
	    ListenableFuture<URI> futureEntity = restTemplate.postForLocation(url, entity, Restaurante.class);

	    futureEntity.addCallback(new ListenableFutureCallback<URI>() {
	        @Override
	        public void onSuccess(URI uri) {
	        	log.info("restuarante inserido com sucesso " + uri.getPath());
	        }

	        @Override
	        public void onFailure(Throwable ex) {
	            result.setErrorResult(ex.getMessage());
	            log.error("Erro ao chamar API para gravação de distância do restaurante.", ex);
				throw new RuntimeException("Problema ao tentar cadastrar distância do restaurante. restauranteId: " + request.getId());
	        }
	    });
	}   
	
	private List<HttpMessageConverter<?>> getMessageConverters() {
	    List<HttpMessageConverter<?>> converters =
	            new ArrayList<>();
	    converters.add(new MappingJackson2HttpMessageConverter());
	    converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
	    return converters;
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