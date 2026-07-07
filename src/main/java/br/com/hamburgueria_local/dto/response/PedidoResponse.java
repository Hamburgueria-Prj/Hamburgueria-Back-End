package br.com.hamburgueria_local.dto.response;

import br.com.hamburgueria_local.enums.PerfilUsuario;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.enums.TipoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {

    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private PerfilUsuario usuarioPerfil;
    private StatusPedido status;
    private TipoPedido tipoPedido;
    private BigDecimal subtotal;
    private BigDecimal desconto;
    private BigDecimal valorTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    private String observacao;
    private List<ItemPedidoResponse> itens;
    private PagamentoResponse pagamento;

    public PedidoResponse() {
    }

    public PedidoResponse(Long id, Long usuarioId, String usuarioNome, PerfilUsuario usuarioPerfil,
                          StatusPedido status, TipoPedido tipoPedido, BigDecimal subtotal, BigDecimal desconto,
                          BigDecimal valorTotal, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao,
                          String observacao, List<ItemPedidoResponse> itens, PagamentoResponse pagamento) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.usuarioPerfil = usuarioPerfil;
        this.status = status;
        this.tipoPedido = tipoPedido;
        this.subtotal = subtotal;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.observacao = observacao;
        this.itens = itens;
        this.pagamento = pagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public PerfilUsuario getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(PerfilUsuario usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
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

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
