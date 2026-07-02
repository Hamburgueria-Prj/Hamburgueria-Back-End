package br.com.hamburgueria_local.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.repositories.UsuarioRepository;
import br.com.hamburgueria_local.enums.PerfilUsuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	private BCryptPasswordEncoder encoder =
	new BCryptPasswordEncoder();
	
	public Usuario adicionaUsuario(Usuario usuario) {
		// CRIPTOGRAFA A SENHA
		String senhaCriptografada =
		encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return repository.save(usuario);
		}
		
}
