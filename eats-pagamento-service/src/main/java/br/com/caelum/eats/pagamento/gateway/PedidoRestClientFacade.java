package br.com.caelum.eats.pagamento.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pagamento.exception.AlteracaoPedidoException;
import br.com.caelum.eats.pagamento.exception.ResourceNotFoundException;
import br.com.caelum.eats.pagamento.repository.PagamentoRepository;
import br.com.caelum.eats.pagamento.repository.entity.Pagamento;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class PedidoRestClientFacade {

	@Autowired
	private final PagamentoRepository pagamentoRepo;

   public void notificaPagamentoDoPedido(Long pedidoId) {
	   
        try {
        	///restClient.notificaPagamentoDoPedido(pedidoId, new PedidoMudancaDeStatusRequest("pago".toUpperCase()));
		} catch (FeignException e) {
			log.error("Erro ao chamar API para notificação do pagamento do pedido.", e);
			throw new AlteracaoPedidoException(pedidoId, e);
		}
        
    }
    
    public void notificaPagamentoDoPedidoFallback(Long pedidoId) {
    	
    	log.info("Entrando no método de fallback. [pedidoId: {}]", pedidoId);
		Pagamento pagamento = pagamentoRepo.findById(pedidoId).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.PROCESSANDO);
		pagamentoRepo.save(pagamento);
    	
    }
}


