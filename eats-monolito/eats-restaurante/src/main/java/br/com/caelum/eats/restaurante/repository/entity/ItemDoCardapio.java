package br.com.caelum.eats.restaurante.repository.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDoCardapio {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(max=150)
	private String nome;

	private String descricao;

	@NotNull @Positive
	private BigDecimal preco;

	@Positive
	private BigDecimal precoPromocional;

	@ManyToOne(optional=false)
	private CategoriaDoCardapio categoria;
	
	public BigDecimal getPrecoEfetivo() {
		return precoPromocional != null ? precoPromocional : preco;
	}
}
