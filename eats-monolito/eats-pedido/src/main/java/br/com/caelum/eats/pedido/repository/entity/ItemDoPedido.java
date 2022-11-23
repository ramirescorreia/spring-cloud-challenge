package br.com.caelum.eats.pedido.repository.entity;

import br.com.caelum.eats.restaurante.repository.entity.ItemDoCardapio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDoPedido {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull @Positive
	private Integer quantidade;

	private String observacao;
	
	@ManyToOne(optional=false)
	private Pedido pedido;

	@ManyToOne(optional=false)
	private ItemDoCardapio itemDoCardapio;

}
