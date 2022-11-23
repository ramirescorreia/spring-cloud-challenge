package br.com.caelum.eats.restaurante.repository.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import br.com.caelum.eats.administrativo.FormaDePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteFormaDePagamento {

	@EmbeddedId
	private RestauranteFormaDePagamentoId id;

	@ManyToOne
	@MapsId("restauranteId")
	private Restaurante restaurante;

	@ManyToOne
	@MapsId("formaDePagamentoId")
	private FormaDePagamento formaDePagamento;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RestauranteFormaDePagamentoId implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "restaurante_id")
		private Long restauranteId;

		@Column(name = "forma_de_pagamento_id")
		private Long formaDePagamentoId;
	}
}
