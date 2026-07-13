package br.com.hamburgueria_local.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hamburgueria_local.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

	Optional<Pagamento> findByPedidoId(Long pedidoId);
}
