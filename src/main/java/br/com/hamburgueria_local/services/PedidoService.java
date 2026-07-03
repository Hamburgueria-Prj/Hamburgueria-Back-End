package br.com.hamburgueria_local.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hamburgueria_local.dto.request.ItemPedidoRequest;
import br.com.hamburgueria_local.dto.request.PedidoRequest;
import br.com.hamburgueria_local.dto.response.ItemPedidoResponse;
import br.com.hamburgueria_local.dto.response.PedidoResponse;
import br.com.hamburgueria_local.entities.Cliente;
import br.com.hamburgueria_local.entities.ItemPedido;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.exceptions.PedidoCanceladoException;
import br.com.hamburgueria_local.exceptions.PedidoSemItensException;
import br.com.hamburgueria_local.exceptions.RecursoNaoEncontradoException;
import br.com.hamburgueria_local.repositories.ClienteRepository;
import br.com.hamburgueria_local.repositories.PedidoRepository;
import br.com.hamburgueria_local.repositories.ProdutoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;

	public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
			ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public PedidoResponse criar(PedidoRequest request) {

		if (request.getItens() == null || request.getItens().isEmpty()) {
			throw new PedidoSemItensException("O pedido deve conter pelo menos um item.");
		}

		Cliente cliente = clienteRepository.findById(request.getClienteId())
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Cliente não encontrado com id: " + request.getClienteId()));

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setTipoPedido(request.getTipoPedido());

		List<ItemPedido> itens = new ArrayList<>();
		BigDecimal subtotal = BigDecimal.ZERO;

		for (ItemPedidoRequest itemRequest : request.getItens()) {
			Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
					.orElseThrow(() -> new RecursoNaoEncontradoException(
							"Produto não encontrado com id: " + itemRequest.getProdutoId()));

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

		pedido.setItens(itens);
		pedido.setSubtotal(subtotal);

		BigDecimal desconto = BigDecimal.ZERO;
		pedido.setDesconto(desconto);
		pedido.setTotal(subtotal.subtract(desconto));
		pedido.setStatus(StatusPedido.RECEBIDO);

		Pedido pedidoSalvo = pedidoRepository.save(pedido);

		return toResponse(pedidoSalvo);
	}

	@Transactional
	public PedidoResponse atualizarStatus(Long pedidoId, StatusPedido novoStatus) {

		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Pedido não encontrado com id: " + pedidoId));

		if (pedido.getStatus() == StatusPedido.CANCELADO) {
			throw new PedidoCanceladoException(
					"Não é possível atualizar um pedido que já está cancelado.");
		}

		pedido.setStatus(novoStatus);

		Pedido pedidoAtualizado = pedidoRepository.save(pedido);

		return toResponse(pedidoAtualizado);
	}

	private PedidoResponse toResponse(Pedido pedido) {

		List<ItemPedidoResponse> itensResponse = new ArrayList<>();
		for (ItemPedido item : pedido.getItens()) {
			ItemPedidoResponse itemResponse = new ItemPedidoResponse();
			itemResponse.setProdutoId(item.getProduto().getId());
			itemResponse.setNomeProduto(item.getProduto().getNome());
			itemResponse.setQuantidade(item.getQuantidade());
			itemResponse.setPrecoUnitario(item.getPrecoUnitario());
			itemResponse.setSubtotalItem(item.getSubtotal());
			itensResponse.add(itemResponse);
		}

		PedidoResponse response = new PedidoResponse();
		response.setId(pedido.getId());
		response.setClienteId(pedido.getCliente().getId());
		response.setStatus(pedido.getStatus());
		response.setTipoPedido(pedido.getTipoPedido());
		response.setSubtotal(pedido.getSubtotal());
		response.setDesconto(pedido.getDesconto());
		response.setValorTotal(pedido.getTotal());
		response.setDataCriacao(pedido.getDataPedido());
		response.setItens(itensResponse);
		// pagamento fica null aqui de proposito - ele e criado depois,
		// num fluxo separado, pelo PagamentoController/PagamentoService

		return response;
	}
}