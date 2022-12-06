package br.com.caelum.eats.restaurante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.administrativo.TipoDeCozinha;
import br.com.caelum.eats.restaurante.controller.domain.RestauranteDto;
import br.com.caelum.eats.restaurante.exception.ResourceNotFoundException;
import br.com.caelum.eats.restaurante.gateway.DistanciaRestauranteRestClientFacade;
import br.com.caelum.eats.restaurante.mapper.RestauranteMapper;
import br.com.caelum.eats.restaurante.repository.CardapioRepository;
import br.com.caelum.eats.restaurante.repository.RestauranteRepository;
import br.com.caelum.eats.restaurante.repository.TipoDeCozinhaRepository;
import br.com.caelum.eats.restaurante.repository.entity.Cardapio;
import br.com.caelum.eats.restaurante.repository.entity.Restaurante;

@Service
public class RestauranteService {

	private final TipoDeCozinhaRepository tipoCozinhaRepo;
	private final RestauranteRepository restauranteRepo;
	private final CardapioRepository cardapioRepo;
	private final RestauranteMapper mapper;
	private final DistanciaRestauranteRestClientFacade restClient;

	@Autowired
	public RestauranteService(TipoDeCozinhaRepository tipoCozRepo, RestauranteRepository repo, CardapioRepository cardapioRepo, RestauranteMapper mapper,
			DistanciaRestauranteRestClientFacade restClient) {
		this.tipoCozinhaRepo = tipoCozRepo;
		this.restauranteRepo = repo;
		this.cardapioRepo = cardapioRepo;
		this.mapper = mapper;
		this.restClient = restClient;
	}

	public Restaurante adiciona(Restaurante restaurante) {
		TipoDeCozinha tipoDeCozinha = this.tipoCozinhaRepo.findById(restaurante.getTipoDeCozinha().getId()).orElseThrow(() -> new ResourceNotFoundException());
		restaurante.setTipoDeCozinha(tipoDeCozinha);
		restaurante.setAprovado(false);
		Restaurante restauranteSalvo = this.restauranteRepo.save(restaurante);

		Cardapio cardapio = new Cardapio();
		cardapio.setRestaurante(restauranteSalvo);

		this.cardapioRepo.save(cardapio);

		this.restClient.criaDistanciaRestaurante(this.mapper.toRequest(restauranteSalvo));

		return restauranteSalvo;
	}
	
	public RestauranteDto atualiza(Long id, RestauranteDto restaurante) {
		
		Restaurante doBD = this.restauranteRepo.getOne(id);
		restaurante.populaRestaurante(doBD);
		
		this.restClient.atualizaDistanciaRestaurante(this.mapper.toRequest(doBD));
		
		return new RestauranteDto(restauranteRepo.save(doBD));
	}

	public Page<Restaurante> findAllByAprovado(boolean aprovado, Pageable limit) {
		return restauranteRepo.findAllByAprovado(aprovado, limit);
	}

	public Page<Restaurante> findAllByAprovadoAndTipoDeCozinha(boolean aprovado, TipoDeCozinha tipo, Pageable limit) {
		return restauranteRepo.findAllByAprovadoAndTipoDeCozinha(aprovado, tipo, limit);
	}

	public Optional<Restaurante> findById(Long id) {
		return restauranteRepo.findById(id);
	}

}
