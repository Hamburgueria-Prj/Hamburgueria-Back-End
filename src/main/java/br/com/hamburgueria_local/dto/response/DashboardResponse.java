package br.com.hamburgueria_local.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import br.com.hamburgueria_local.enums.StatusPedido;

public class DashboardResponse {

	private BigDecimal totalVendidoDia;
	private Long quantidadePedidosDia;
	private List<ItemMaisVendidoResponse> itensMaisVendidos;
	private Map<StatusPedido, Long> pedidosPorStatus;

	public DashboardResponse() {
	}

	public DashboardResponse(BigDecimal totalVendidoDia, Long quantidadePedidosDia,
			List<ItemMaisVendidoResponse> itensMaisVendidos, Map<StatusPedido, Long> pedidosPorStatus) {
		this.totalVendidoDia = totalVendidoDia;
		this.quantidadePedidosDia = quantidadePedidosDia;
		this.itensMaisVendidos = itensMaisVendidos;
		this.pedidosPorStatus = pedidosPorStatus;
	}

	public BigDecimal getTotalVendidoDia() {
		return totalVendidoDia;
	}

	public void setTotalVendidoDia(BigDecimal totalVendidoDia) {
		this.totalVendidoDia = totalVendidoDia;
	}

	public Long getQuantidadePedidosDia() {
		return quantidadePedidosDia;
	}

	public void setQuantidadePedidosDia(Long quantidadePedidosDia) {
		this.quantidadePedidosDia = quantidadePedidosDia;
	}

	public List<ItemMaisVendidoResponse> getItensMaisVendidos() {
		return itensMaisVendidos;
	}

	public void setItensMaisVendidos(List<ItemMaisVendidoResponse> itensMaisVendidos) {
		this.itensMaisVendidos = itensMaisVendidos;
	}

	public Map<StatusPedido, Long> getPedidosPorStatus() {
		return pedidosPorStatus;
	}

	public void setPedidosPorStatus(Map<StatusPedido, Long> pedidosPorStatus) {
		this.pedidosPorStatus = pedidosPorStatus;
	}
}
