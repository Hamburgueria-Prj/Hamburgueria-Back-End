package br.com.hamburgueria_local.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.enums.TipoPedido;

public class PedidoResponse {

	private Long id;
	private Long clienteId;
	private StatusPedido status;
	private TipoPedido tipoPedido;
	private BigDecimal subtotal;
	private BigDecimal desconto;
	private BigDecimal valorTotal;
	private LocalDateTime dataCriacao;
	private List<ItemPedidoResponse> itens;
	private PagamentoResponse pagamento;

	public PedidoResponse() {
	}

	public PedidoResponse(Long id, Long clienteId, StatusPedido status, TipoPedido tipoPedido,
			BigDecimal subtotal, BigDecimal desconto, BigDecimal valorTotal, LocalDateTime dataCriacao,
			List<ItemPedidoResponse> itens, PagamentoResponse pagamento) {
		this.id = id;
		this.clienteId = clienteId;
		this.status = status;
		this.tipoPedido = tipoPedido;
		this.subtotal = subtotal;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.itens = itens;
		this.pagamento = pagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public TipoPedido getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(TipoPedido tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<ItemPedidoResponse> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoResponse> itens) {
		this.itens = itens;
	}

	public PagamentoResponse getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoResponse pagamento) {
		this.pagamento = pagamento;
	}
}
