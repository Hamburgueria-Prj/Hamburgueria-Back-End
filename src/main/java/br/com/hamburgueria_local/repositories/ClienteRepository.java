
package br.com.hamburgueria_local.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hamburgueria_local.entities.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByAtivoTrue();
}
