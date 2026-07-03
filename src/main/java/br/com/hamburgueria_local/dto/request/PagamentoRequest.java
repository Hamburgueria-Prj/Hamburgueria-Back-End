package br.com.hamburgueria_local.dto.request;

import java.math.BigDecimal;

import br.com.hamburgueria_local.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;

public class PagamentoRequest {
    @NotNull private Long pedidoId;
    @NotNull private FormaPagamento formaPagamento;
    @NotNull private BigDecimal valorPago;
    private BigDecimal trocoPara; // so obrigatorio se DINHEIRO

    public PagamentoRequest() {
    }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }


    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
    public BigDecimal getTrocoPara() { return trocoPara; }
    public void setTrocoPara(BigDecimal trocoPara) { this.trocoPara = trocoPara; }
}


