package br.com.hamburgueria_local.repositories;

import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

