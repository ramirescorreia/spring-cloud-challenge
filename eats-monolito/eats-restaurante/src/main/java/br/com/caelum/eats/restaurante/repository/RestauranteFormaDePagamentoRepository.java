package br.com.caelum.eats.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.caelum.eats.administrativo.FormaDePagamento;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;
import br.com.caelum.eats.restaurante.repository.entity.RestauranteFormaDePagamento;

public interface RestauranteFormaDePagamentoRepository extends JpaRepository<RestauranteFormaDePagamento, RestauranteFormaDePagamento.RestauranteFormaDePagamentoId> {

	@Query("select f from RestauranteFormaDePagamento rf join rf.restaurante r join rf.formaDePagamento f where r = :restaurante order by f.nome")
	List<FormaDePagamento> findAllByRestauranteOrderByNomeAsc(@Param("restaurante") Restaurante restaurante);

}
