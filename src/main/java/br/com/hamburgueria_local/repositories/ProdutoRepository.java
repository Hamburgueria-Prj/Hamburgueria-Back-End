package br.com.hamburgueria_local.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByAtivoTrue();
    List<Produto> findByCategoria(CategoriaProduto categoria);
}

