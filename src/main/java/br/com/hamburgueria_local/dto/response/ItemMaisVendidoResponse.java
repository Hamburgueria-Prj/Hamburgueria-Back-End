package br.com.hamburgueria_local.dto.response;

public class ItemMaisVendidoResponse {

	private Long produtoId;
	private String nomeProduto;
	private Integer quantidadeVendida;

	public ItemMaisVendidoResponse() {
	}

	public ItemMaisVendidoResponse(Long produtoId, String nomeProduto, Integer quantidadeVendida) {
		this.produtoId = produtoId;
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
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

	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}
}
