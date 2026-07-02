package br.com.hamburgueria_local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.hamburgueria_local.dto.request.UsuarioRequest;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.services.UsuarioService;
import jakarta.validation.Valid;
import br.com.hamburgueria_local.enums.PerfilUsuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@PostMapping
	public Usuario adicionaUsuario(@RequestBody @Valid UsuarioRequest request) {
		// Converte o DTO para a Entidade antes de mandar pro Service
		Usuario usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		usuario.setPerfil(request.getPerfil());
		
		return service.adicionaUsuario(usuario);
	}
}
