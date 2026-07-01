package br.com.hamburgueria_local.dto.request;

import java.util.List;

import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.TipoPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PedidoRequest {

	@NotNull(message = "O cliente é obrigatório")
	private Long clienteId;

	@NotNull(message = "O tipo do pedido é obrigatório")
	private TipoPedido tipoPedido;

	@NotNull(message = "A forma de pagamento é obrigatória")
	private FormaPagamento formaPagamento;

	@NotEmpty(message = "O pedido deve conter pelo menos um item")
	@Valid
	private List<ItemPedidoRequest> itens;

	public PedidoRequest() {
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public TipoPedido getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(TipoPedido tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ItemPedidoRequest> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoRequest> itens) {
		this.itens = itens;
	}
}
