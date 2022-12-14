package br.com.caelum.eats.pagamento.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.caelum.eats.pagamento.exception.ResourceNotFoundException;
import br.com.caelum.eats.pagamento.gateway.domain.PedidoDto;
import br.com.caelum.eats.pagamento.gateway.domain.PedidoMudancaDeStatusRequest;
import br.com.caelum.eats.pagamento.repository.PagamentoRepository;
import br.com.caelum.eats.pagamento.repository.entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Service
public class PedidoRestClientFacade {

	@Autowired
	private final PagamentoRepository pagamentoRepo;

	@Autowired
	WebClient webClient;

	public void notificaPagamentoDoPedido(Long pedidoId) {

		PedidoMudancaDeStatusRequest status = new PedidoMudancaDeStatusRequest("pago".toUpperCase());
		Mono<PedidoDto> mono =  this.webClient
				.put()
				.uri("/pedidos/"+pedidoId+"/status")
				.body(Mono.just(status), PedidoMudancaDeStatusRequest.class)
				//.body(BodyInserters.fromValue(status))
				.retrieve()
				.onStatus(HttpStatus::is1xxInformational,
						error -> Mono.error(new RuntimeException("Erro 1xx")))
				.onStatus(HttpStatus::is3xxRedirection,
						error -> Mono.error(new RuntimeException("Erro 3xx")))
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Erro 4xx")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new RuntimeException("Erro 5xx")))
				.bodyToMono(PedidoDto.class);
		
//		mono.block();	
		mono.subscribe(p->{
			System.out.print("/nFinalizado notificação WebClient para o pedido "+ pedidoId+ "- Status -"+p.getStatus());
		});
	}

	public void notificaPagamentoDoPedidoFallback(Long pedidoId) {

		log.info("Entrando no método de fallback. [pedidoId: {}]", pedidoId);
		Pagamento pagamento = pagamentoRepo.findById(pedidoId).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.PROCESSANDO);
		pagamentoRepo.save(pagamento);

	}
}
