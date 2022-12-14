package br.com.caelum.eats.restaurante.gateway.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;


public class RestauranteRequest {

	private Long id;

	private String cep;

	private Long tipoDeCozinhaId;
	
	

	public RestauranteRequest(Long id, String cep, Long tipoDeCozinhaId) {
		super();
		this.id = id;
		this.cep = cep;
		this.tipoDeCozinhaId = tipoDeCozinhaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getTipoDeCozinhaId() {
		return tipoDeCozinhaId;
	}

	public void setTipoDeCozinhaId(Long tipoDeCozinhaId) {
		this.tipoDeCozinhaId = tipoDeCozinhaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestauranteRequest other = (RestauranteRequest) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
