package br.com.hamburgueria_local.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.dto.request.UsuarioRequest;
import br.com.hamburgueria_local.dto.response.UsuarioResponse;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



// Adicionar 
public Usuario adicionaUsuario(Usuario usuario) {
		// CRIPTOGRAFA A SENHA
		String senhaCriptografada =
		encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return repository.save(usuario);
	}
// listar ativos

public List<UsuarioResponse> listarAtivos() {
	        
		 return repository.findByAtivoTrue()
	              .stream()
	              .map(UsuarioResponse::new)
	              .toList();
	 }
	 
// listar por id
public UsuarioResponse buscarPorId(Long id) {
		 Usuario user = buscarEntidadePorId(id);
		 return new UsuarioResponse(user);
	 }
	 
// editar 
public UsuarioResponse atualizar(Long id, UsuarioRequest dto) {
	        Usuario existente = buscarEntidadePorId(id);

	        existente.setNome(dto.getNome());
	        existente.setEmail(dto.getEmail());
	        existente.setPerfil(dto.getPerfil());
	        
	        if (existente.getSenha() != null && !existente.getSenha().isBlank()) {
	        	existente.setSenha(encoder.encode(existente.getSenha()));
			}
	        
	        Usuario atualizado = repository.save(existente);

	        return new UsuarioResponse(atualizado);
	  }
	
// inativar
public void inativar(Long id) {
              Usuario user = buscarEntidadePorId(id);
              user.setAtivo(false);
              repository.save(user);
}	 
	 
	 
	 
	 
	 

	  private Usuario buscarEntidadePorId(Long id) {
	        return repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	    }


	
	
}
