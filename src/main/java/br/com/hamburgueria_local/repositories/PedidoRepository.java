package br.com.hamburgueria_local.repositories;

import br.com.hamburgueria_local.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioIdOrderByIdDesc(Long usuarioId);
}
