package br.com.hamburgueria_local.repositories;

import br.com.hamburgueria_local.entities.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailIgnoreCase(String email);
    Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<Usuario> findByAtivoTrue();
}
