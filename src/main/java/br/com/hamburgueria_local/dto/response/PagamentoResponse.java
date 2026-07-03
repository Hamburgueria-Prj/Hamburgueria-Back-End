package br.com.hamburgueria_local.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.StatusPagamento;

	public class PagamentoResponse {
	    private Long id;
	    private Long pedidoId;
	    private FormaPagamento formaPagamento;
	    private StatusPagamento statusPagamento;
	    private BigDecimal valorPago;
	    private BigDecimal trocoPara;
	    private BigDecimal troco;
	    private LocalDateTime dataPagamento;

	    public PagamentoResponse() {
	    }

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public Long getPedidoId() { return pedidoId; }
	    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
	    public FormaPagamento getFormaPagamento() { return formaPagamento; }
	    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
	    public StatusPagamento getStatusPagamento() { return statusPagamento; }
	    public void setStatusPagamento(StatusPagamento statusPagamento) { this.statusPagamento = statusPagamento; }
	    public BigDecimal getValorPago() { return valorPago; }
	    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
	    public BigDecimal getTrocoPara() { return trocoPara; }
	    public void setTrocoPara(BigDecimal trocoPara) { this.trocoPara = trocoPara; }
	    public BigDecimal getTroco() { return troco; }
	    public void setTroco(BigDecimal troco) { this.troco = troco; }
	    public LocalDateTime getDataPagamento() { return dataPagamento; }
	    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }
	}


	


