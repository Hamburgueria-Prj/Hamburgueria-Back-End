package br.com.hamburgueria_local.services;


import br.com.hamburgueria_local.dto.request.ProdutoRequest;
import br.com.hamburgueria_local.dto.response.ProdutoResponse;
import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponse salvar(ProdutoRequest dto) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setAtivo(true);

        Produto salvo = repository.save(produto);

        return new ProdutoResponse(salvo);
    }

    public List<ProdutoResponse> listarAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(ProdutoResponse::new)
                .toList();
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = buscarEntidadePorId(id);
        return new ProdutoResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest dto) {
        Produto existente = buscarEntidadePorId(id);

        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());
        existente.setPreco(dto.getPreco());
        existente.setCategoria(dto.getCategoria());

        Produto atualizado = repository.save(existente);

        return new ProdutoResponse(atualizado);
    }

    public void inativar(Long id) {
        Produto produto = buscarEntidadePorId(id);
        produto.setAtivo(false);
        repository.save(produto);
    }

    private Produto buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}