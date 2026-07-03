package br.com.hamburgueria_local.entities;
import br.com.hamburgueria_local.enums.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@Entity @Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private StatusPedido status = StatusPedido.RECEBIDO;
    @Enumerated(EnumType.STRING) @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;
    @Column(precision = 10, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;
    @Column(precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;
    @Column(length = 255)
    private String observacao;
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido = LocalDateTime.now();
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, Usuario usuario, StatusPedido status, TipoPedido tipoPedido, BigDecimal subtotal, BigDecimal desconto, BigDecimal total, String observacao, LocalDateTime dataPedido, LocalDateTime dataAtualizacao, List<ItemPedido> itens) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.status = status;
        this.tipoPedido = tipoPedido;
        this.subtotal = subtotal;
        this.desconto = desconto;
        this.total = total;
        this.observacao = observacao;
        this.dataPedido = dataPedido;
        this.dataAtualizacao = dataAtualizacao;
        this.itens = itens;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public TipoPedido getTipoPedido() { return tipoPedido; }
    public void setTipoPedido(TipoPedido tipoPedido) { this.tipoPedido = tipoPedido; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}

