package br.com.caelum.eats.pedido.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@NotBlank
	@Size(max = 100)
	@Column(name = "nome_do_cliente")
	private String nome;

	@NotBlank
	@Size(max = 14)
	@Column(name = "cpf_do_cliente")
	private String cpf;

	@NotBlank
	@Size(max = 100)
	@Column(name = "email_do_cliente")
	private String email;

	@NotBlank
	@Size(max = 16)
	@Column(name = "telefone_do_cliente")
	private String telefone;

}
