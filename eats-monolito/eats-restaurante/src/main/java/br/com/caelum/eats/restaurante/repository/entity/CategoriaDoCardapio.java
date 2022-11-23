package br.com.caelum.eats.restaurante.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDoCardapio {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Size(max=100)
	private String nome;

	@ManyToOne(optional=false)
	private Cardapio cardapio;

	@OneToMany(mappedBy="categoria")
	private List<ItemDoCardapio> itens = new ArrayList<>();
	
}
