package br.com.hamburgueria_local.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria_local.dto.request.PagamentoRequest;
import br.com.hamburgueria_local.dto.response.PagamentoResponse;
import br.com.hamburgueria_local.services.PagamentoService;
import jakarta.validation.Valid;

@RestController @RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService service;

	@PostMapping
	public ResponseEntity<PagamentoResponse> criar(@Valid @RequestBody PagamentoRequest request) {
		return ResponseEntity.ok(service.salvar(request));
	}

	@GetMapping
	public ResponseEntity<List<PagamentoResponse>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarPorId(id));
	}

}