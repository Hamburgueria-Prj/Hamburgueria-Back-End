package br.com.hamburgueria_local.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "pedido_id", nullable = false,unique = true) 
	private Pedido pedido;

	@Enumerated(EnumType.STRING) @Column(name = "forma_agamento", nullable = false)
	private FormaPagamento formaPagamento;

	@Enumerated(EnumType.STRING) @Column(name = "status_pagamento", nullable = false)
	private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
	
	@Column(name = "valor_pago", precision = 10, scale = 2)
	private BigDecimal valorPago;
	
	@Column ( name = "troco_para", precision = 10, scale = 2)
	private BigDecimal trocoPara;
	
	@Column (precision = 10, scale = 2)
	private BigDecimal troco;
	
	@Column (name = "data_pagamento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataPagamento;
	
	public Pagamento() {
		
	}

	public Pagamento(Long id, Pedido pedido, FormaPagamento formaPagamento, StatusPagamento statusPagamento,
			BigDecimal valorPago, BigDecimal trocoPara, BigDecimal troco, LocalDateTime dataPagamento) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.formaPagamento = formaPagamento;
		this.statusPagamento = statusPagamento;
		this.valorPago = valorPago;
		this.trocoPara = trocoPara;
		this.troco = troco;
		this.dataPagamento = dataPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valoPagor) {
		this.valorPago = valoPagor;
	}

	public BigDecimal getTrocoPara() {
		return trocoPara;
	}

	public void setTrocoPara(BigDecimal trocoPara) {
		this.trocoPara = trocoPara;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}