package br.com.caelum.eats.pagamento.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
