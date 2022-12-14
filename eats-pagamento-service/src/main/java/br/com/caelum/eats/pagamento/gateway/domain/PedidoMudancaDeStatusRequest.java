package br.com.caelum.eats.pagamento.gateway.domain;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class PedidoMudancaDeStatusRequest {
	
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PedidoMudancaDeStatusRequest(String status) {
		super();
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoMudancaDeStatusRequest other = (PedidoMudancaDeStatusRequest) obj;
		return Objects.equals(status, other.status);
	}
    
    
    
    
    
}