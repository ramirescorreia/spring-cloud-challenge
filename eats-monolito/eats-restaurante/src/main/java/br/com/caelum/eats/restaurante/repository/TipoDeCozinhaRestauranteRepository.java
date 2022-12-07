package br.com.caelum.eats.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.caelum.eats.administrativo.TipoDeCozinha;

public interface TipoDeCozinhaRestauranteRepository extends JpaRepository<TipoDeCozinha, Long>{

}