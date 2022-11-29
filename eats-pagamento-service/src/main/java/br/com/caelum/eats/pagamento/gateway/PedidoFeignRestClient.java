package br.com.caelum.eats.pagamento.gateway;

import br.com.caelum.eats.pagamento.gateway.domain.PedidoMudancaDeStatusRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PedidoFeignRestClient {

	@RequestLine(value = "PUT /pedidos/{pedidoId}/status")
	@Headers("Content-Type: application/json")
	public void notificaPagamentoDoPedido(@Param("pedidoId") Long pedidoId, PedidoMudancaDeStatusRequest request);
	
}
