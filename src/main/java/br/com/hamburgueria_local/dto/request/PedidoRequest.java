package br.com.hamburgueria_local.dto.request;



import java.math.BigDecimal;
import java.util.List;

import br.com.hamburgueria_local.enums.TipoPedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class PedidoRequest {
    private Long clienteId;       // opcional (pedido avulso)
    @NotNull private Long usuarioId;
    @NotNull private TipoPedido tipoPedido;
    private BigDecimal desconto;
    private String observacao;
    @NotEmpty(message = "Pedido precisa ter ao menos um item")
    private List<ItemPedidoRequest> itens;

    public PedidoRequest() {
    }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public TipoPedido getTipoPedido() { return tipoPedido; }
    public void setTipoPedido(TipoPedido tipoPedido) { this.tipoPedido = tipoPedido; }
    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public List<ItemPedidoRequest> getItens() { return itens; }
    public void setItens(List<ItemPedidoRequest> itens) { this.itens = itens; }
}
