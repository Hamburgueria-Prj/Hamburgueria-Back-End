package br.com.hamburgueria_local.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.com.hamburgueria_local.dto.request.UsuarioRequest;

import br.com.hamburgueria_local.dto.response.UsuarioResponse;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.services.UsuarioService;
import jakarta.validation.Valid;


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
	   @GetMapping
	    public ResponseEntity<List<UsuarioResponse>> listarAtivos() {
	      
		   List<UsuarioResponse> user = service.listarAtivos();
	       
		   return ResponseEntity.ok(user);
	    }
	
	   @GetMapping("/{id}")
	    public ResponseEntity<UsuarioResponse> BuscarPorId(@PathVariable Long id) {
		   UsuarioResponse cliente = service.buscarPorId(id);
	        return ResponseEntity.ok(cliente);
	    }
	   @PutMapping("/{id}")
	    public ResponseEntity<UsuarioResponse> atualizar(
	            @PathVariable Long id,
	            @Valid @RequestBody UsuarioRequest request
	    ) 
	    {
		   UsuarioResponse  cliente = service.atualizar(id, request);
	        return ResponseEntity.ok(cliente);
	    }
	   
	   
	   @PatchMapping("/{id}/inativar")
	    public ResponseEntity<Void> inativar(@PathVariable Long id) {
	        service.inativar(id);
	        return ResponseEntity.noContent().build();
	    }
	
	
	
	
	
}
