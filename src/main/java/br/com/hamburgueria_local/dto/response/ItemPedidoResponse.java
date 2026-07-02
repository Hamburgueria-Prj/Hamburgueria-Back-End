package br.com.hamburgueria_local.dto.response;

import java.math.BigDecimal;

public class ItemPedidoResponse {

	private Long produtoId;
	private String nomeProduto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal subtotalItem;

	public ItemPedidoResponse() {
	}

	public ItemPedidoResponse(Long produtoId, String nomeProduto, Integer quantidade,
			BigDecimal precoUnitario, BigDecimal subtotalItem) {
		this.produtoId = produtoId;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.subtotalItem = subtotalItem;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getSubtotalItem() {
		return subtotalItem;
	}

	public void setSubtotalItem(BigDecimal subtotalItem) {
		this.subtotalItem = subtotalItem;
	}
}
