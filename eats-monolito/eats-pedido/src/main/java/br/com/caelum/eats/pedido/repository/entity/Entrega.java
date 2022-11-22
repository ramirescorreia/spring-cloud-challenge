package br.com.caelum.eats.pedido.repository.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Cliente cliente;

	@NotBlank
	@Size(max=9)
	private String cep;

	@NotBlank
	@Size(max=255)
	private String endereco;

	@Size(max=255)
	private String complemento;

	@OneToOne(optional=false)
	private Pedido pedido;

}
