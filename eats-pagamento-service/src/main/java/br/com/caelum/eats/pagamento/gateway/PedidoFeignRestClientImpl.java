package br.com.caelum.eats.pagamento.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.caelum.eats.pagamento.gateway.domain.PedidoMudancaDeStatusRequest;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Component
@Import(FeignClientsConfiguration.class)
public class PedidoFeignRestClientImpl {

	private PedidoFeignRestClient restClientInterface;
	
	@Autowired
	public PedidoFeignRestClientImpl(Decoder decoder, Encoder encoder, Client client) {
		this.restClientInterface = Feign.builder().client(client).encoder(encoder).decoder(decoder)
				.target(PedidoFeignRestClient.class, "eats-monolito");
	}
	
	@PutMapping("/pedidos/{pedidoId}/status")
	void notificaPagamentoDoPedido(@PathVariable("pedidoId") Long pedidoId, PedidoMudancaDeStatusRequest request) {
		this.restClientInterface.notificaPagamentoDoPedido(pedidoId, request);
	}
}
