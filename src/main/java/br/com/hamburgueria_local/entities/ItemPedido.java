package br.com.hamburgueria_local.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "itens_pedido")
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    @ManyToOne @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario; // copiado do produto no momento da venda
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;      // quantidade x precoUnitario
    @Column(length = 255)
    private String observacao;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Pedido pedido, Produto produto, Integer quantidade, BigDecimal precoUnitario, BigDecimal subtotal, String observacao) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
        this.observacao = observacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
