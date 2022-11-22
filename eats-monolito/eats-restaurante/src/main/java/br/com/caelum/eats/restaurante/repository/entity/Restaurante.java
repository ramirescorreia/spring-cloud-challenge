package br.com.caelum.eats.restaurante.repository.entity;

import java.math.BigDecimal;

import br.com.caelum.eats.administrativo.TipoDeCozinha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(max=18)
	private String cnpj;
	
	@NotBlank @Size(max=255)
	private String nome;
	
	@Size(max=1000)
	private String descricao;

	@NotBlank @Size(max=9)
	private String cep;

	@NotBlank @Size(max=300)
	private String endereco;

	@Positive
	private BigDecimal taxaDeEntregaEmReais;
	
	@Positive @Min(10) @Max(180)
	private Integer tempoDeEntregaMinimoEmMinutos;
	
	@Positive @Min(10) @Max(180)
	private Integer tempoDeEntregaMaximoEmMinutos;
	
	private Boolean aprovado;

	@ManyToOne(optional=false)
	private TipoDeCozinha tipoDeCozinha;


}
