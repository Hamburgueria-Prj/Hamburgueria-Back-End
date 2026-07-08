package br.com.hamburgueria_local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria_local.dto.response.DashboardResponse;
import br.com.hamburgueria_local.services.DashboardService;

@RestController @RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService service;

	@GetMapping
	public ResponseEntity<DashboardResponse> gerarDashboardDoDia() {
		return ResponseEntity.ok(service.gerarDashboardDoDia());
	}

}
