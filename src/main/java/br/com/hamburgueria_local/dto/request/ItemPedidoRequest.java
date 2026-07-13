package br.com.hamburgueria_local.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemPedidoRequest {

	@NotNull(message = "O produto é obrigatório")
	private Long produtoId;

	@NotNull(message = "A quantidade é obrigatória")
	@Positive(message = "A quantidade deve ser maior que zero")
	private Integer quantidade;

	public ItemPedidoRequest() {
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
