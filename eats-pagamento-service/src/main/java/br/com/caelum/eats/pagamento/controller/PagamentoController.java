package br.com.caelum.eats.pagamento.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.eats.pagamento.controller.domain.PagamentoDto;
import br.com.caelum.eats.pagamento.exception.ResourceNotFoundException;
import br.com.caelum.eats.pagamento.repository.PagamentoRepository;
import br.com.caelum.eats.pagamento.repository.entity.Pagamento;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private final PagamentoRepository pagamentoRepo;
	private final KafkaTemplate<String, Pagamento> kafkaTemplate;
	
	public PagamentoController(PagamentoRepository pagamentoRepo, KafkaTemplate<String, Pagamento> kafkaTemplate) {
		this.pagamentoRepo = pagamentoRepo;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Value("${kafka.topics.pedido.confirma.pagamento}")
	private String confirmaPagamentoTopic;
	
	private static final Integer PARTITION_EATS_PAGAMENTO = 0;
//	private static final Integer PARTITION_EATS_MONOLITO_PEDIDO = 1;

	@GetMapping
	public ResponseEntity<List<PagamentoDto>> lista() {
		return ResponseEntity.ok(pagamentoRepo.findAll()
				.stream()
				.map(PagamentoDto::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public PagamentoDto detalha(@PathVariable("id") Long id) {
		return pagamentoRepo.findById(id)
				.map(PagamentoDto::new)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(path).body(new PagamentoDto(salvo));
	}

	@PutMapping("/{id}")
	public PagamentoDto confirma(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		kafkaTemplate.send(confirmaPagamentoTopic, PARTITION_EATS_PAGAMENTO, id.toString(), pagamento);
//		kafkaTemplate.send(confirmaPagamentoTopic, PARTITION_EATS_MONOLITO_PEDIDO, id.toString(), pagamento);
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}