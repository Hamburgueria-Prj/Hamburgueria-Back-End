package br.com.hamburgueria_local.services;

import br.com.hamburgueria_local.dto.request.ItemPedidoRequest;
import br.com.hamburgueria_local.dto.request.PedidoRequest;
import br.com.hamburgueria_local.dto.response.ItemPedidoResponse;
import br.com.hamburgueria_local.dto.response.PagamentoResponse;
import br.com.hamburgueria_local.dto.response.PedidoResponse;
import br.com.hamburgueria_local.entities.ItemPedido;
import br.com.hamburgueria_local.entities.Pagamento;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.exceptions.PedidoCanceladoException;
import br.com.hamburgueria_local.exceptions.PedidoSemItensException;
import br.com.hamburgueria_local.exceptions.RecursoNaoEncontradoException;
import br.com.hamburgueria_local.repositories.PagamentoRepository;
import br.com.hamburgueria_local.repositories.PedidoRepository;
import br.com.hamburgueria_local.repositories.ProdutoRepository;
import br.com.hamburgueria_local.repositories.UsuarioRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagamentoRepository pagamentoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         UsuarioRepository usuarioRepository,
                         PagamentoRepository pagamentoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Transactional
    public PedidoResponse criar(PedidoRequest request) {
        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new PedidoSemItensException("O pedido deve conter pelo menos um item.");
        }

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Usuário não encontrado com id: " + request.getUsuarioId()
                ));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTipoPedido(request.getTipoPedido());
        pedido.setObservacao(request.getObservacao());

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (ItemPedidoRequest itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Produto não encontrado com id: " + itemRequest.getProdutoId()
                    ));

            BigDecimal precoAtual = produto.getPreco();
            BigDecimal subtotalItem = precoAtual.multiply(BigDecimal.valueOf(itemRequest.getQuantidade()));

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPrecoUnitario(precoAtual);
            item.setSubtotal(subtotalItem);
            itens.add(item);
            subtotal = subtotal.add(subtotalItem);
        }

        BigDecimal desconto = request.getDesconto() != null ? request.getDesconto() : BigDecimal.ZERO;
        if (desconto.compareTo(BigDecimal.ZERO) < 0) {
            desconto = BigDecimal.ZERO;
        }
        if (desconto.compareTo(subtotal) > 0) {
            desconto = subtotal;
        }

        pedido.setItens(itens);
        pedido.setSubtotal(subtotal);
        pedido.setDesconto(desconto);
        pedido.setTotal(subtotal.subtract(desconto));
        pedido.setStatus(StatusPedido.RECEBIDO);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return toResponse(pedidoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(this::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<PedidoResponse> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Usuário não encontrado com id: " + usuarioId
                ));

        return pedidoRepository.findByUsuarioIdOrderByIdDesc(usuario.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido não encontrado com id: " + id
                ));

        return toResponse(pedido);
    }

    @Transactional
    public PedidoResponse atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido não encontrado com id: " + pedidoId
                ));

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new PedidoCanceladoException(
                    "Não é possível atualizar um pedido que já está cancelado."
            );
        }

        pedido.setStatus(novoStatus);

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return toResponse(pedidoAtualizado);
    }

    private PedidoResponse toResponse(Pedido pedido) {
        List<ItemPedidoResponse> itensResponse = new ArrayList<>();

        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                ItemPedidoResponse itemResponse = new ItemPedidoResponse();
                itemResponse.setProdutoId(item.getProduto().getId());
                itemResponse.setNomeProduto(item.getProduto().getNome());
                itemResponse.setQuantidade(item.getQuantidade());
                itemResponse.setPrecoUnitario(item.getPrecoUnitario());
                itemResponse.setSubtotalItem(item.getSubtotal());
                itensResponse.add(itemResponse);
            }
        }

        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());

        if (pedido.getUsuario() != null) {
            response.setUsuarioId(pedido.getUsuario().getId());
            response.setUsuarioNome(pedido.getUsuario().getNome());
            response.setUsuarioPerfil(pedido.getUsuario().getPerfil());
        }

        response.setStatus(pedido.getStatus());
        response.setTipoPedido(pedido.getTipoPedido());
        response.setSubtotal(pedido.getSubtotal());
        response.setDesconto(pedido.getDesconto());
        response.setValorTotal(pedido.getTotal());
        response.setDataCriacao(pedido.getDataPedido());
        response.setDataAtualizacao(pedido.getDataAtualizacao());
        response.setObservacao(pedido.getObservacao());
        response.setItens(itensResponse);

        pagamentoRepository.findByPedidoId(pedido.getId())
                .map(this::pagamentoToResponse)
                .ifPresent(response::setPagamento);

        return response;
    }

    private PagamentoResponse pagamentoToResponse(Pagamento pagamento) {
        PagamentoResponse response = new PagamentoResponse();
        response.setId(pagamento.getId());
        response.setPedidoId(pagamento.getPedido().getId());
        response.setFormaPagamento(pagamento.getFormaPagamento());
        response.setStatusPagamento(pagamento.getStatusPagamento());
        response.setValorPago(pagamento.getValorPago());
        response.setTrocoPara(pagamento.getTrocoPara());
        response.setTroco(pagamento.getTroco());
        response.setDataPagamento(pagamento.getDataPagamento());
        return response;
    }
}
