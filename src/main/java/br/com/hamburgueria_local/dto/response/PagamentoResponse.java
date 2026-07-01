package br.com.hamburgueria_local.dto.response;

import java.math.BigDecimal;

import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.StatusPagamento;

public class PagamentoResponse {

	private Long id;
	private StatusPagamento status;
	private FormaPagamento formaPagamento;
	private BigDecimal valor;

	public PagamentoResponse() {
	}

	public PagamentoResponse(Long id, StatusPagamento status, FormaPagamento formaPagamento, BigDecimal valor) {
		this.id = id;
		this.status = status;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
