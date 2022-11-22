package br.com.caelum.eats.pagamento.repository.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

	public static enum Status {
		CRIADO,
		PROCESSANDO,
		CONFIRMADO,
		CANCELADO;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NonNull 
	private BigDecimal valor;


	private String nome;


	private String numero;


	private String expiracao;
	

	private String codigo;


	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable=false)
	private Long pedidoId;

	@Column(nullable=false)
	private Long formaDePagamentoId;

}
