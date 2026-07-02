package br.com.hamburgueria_local.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.hamburgueria_local.dto.request.PedidoRequest;
import br.com.hamburgueria_local.dto.response.PedidoResponse;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.services.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@PostMapping
	public ResponseEntity<PedidoResponse> criar(@RequestBody @Valid PedidoRequest request) {
		PedidoResponse response = pedidoService.criar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id,
			@RequestParam StatusPedido status) {
		PedidoResponse response = pedidoService.atualizarStatus(id, status);
		return ResponseEntity.ok(response);
	}
}
