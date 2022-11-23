package br.com.caelum.eats.restaurante.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cardapio {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional=false)
	private Restaurante restaurante;

	@OneToMany(mappedBy="cardapio")
	private List<CategoriaDoCardapio> categorias = new ArrayList<>();

}
