package br.com.hamburgueria_local.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria_local.dto.request.PagamentoRequest;
import br.com.hamburgueria_local.dto.response.PagamentoResponse;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.StatusPagamento;
import br.com.hamburgueria_local.services.PedidoService;
import jakarta.validation.Valid;

@RestController @RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired 
	private PedidoService service;
	
	@PostMapping
	public ResponseEntity<PagamentoResponse> criar(@Valid @RequestBody PagamentoRequest request ){
		return ResponseEntity.ok(service.criar(request))
	}
	
	

}
