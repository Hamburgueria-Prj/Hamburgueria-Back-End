package br.com.hamburgueria_local.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hamburgueria_local.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
