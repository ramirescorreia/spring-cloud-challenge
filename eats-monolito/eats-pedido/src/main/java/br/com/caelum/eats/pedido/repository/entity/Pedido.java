package br.com.caelum.eats.pedido.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.eats.restaurante.repository.entity.Restaurante;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	
	public static enum Status {
		REALIZADO,
		PAGO,
		CONFIRMADO,
		PRONTO,
		SAIU_PARA_ENTREGA,
		ENTREGUE;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataHora;

	@NotNull @Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(optional=false)
	private Restaurante restaurante;

	@OneToOne(cascade=CascadeType.PERSIST, optional=false, mappedBy="pedido")
	private Entrega entrega;

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="pedido")
	private List<ItemDoPedido> itens = new ArrayList<>();

}
