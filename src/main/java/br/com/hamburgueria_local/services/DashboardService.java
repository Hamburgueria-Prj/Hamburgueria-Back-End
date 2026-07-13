package br.com.hamburgueria_local.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.dto.response.DashboardResponse;
import br.com.hamburgueria_local.dto.response.ItemMaisVendidoResponse;
import br.com.hamburgueria_local.entities.ItemPedido;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.entities.Produto;
import br.com.hamburgueria_local.enums.StatusPedido;
import br.com.hamburgueria_local.repositories.PedidoRepository;

@Service
public class DashboardService {

	private static final int LIMITE_ITENS_MAIS_VENDIDOS = 5;

	private final PedidoRepository pedidoRepository;

	public DashboardService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public DashboardResponse gerarDashboardDoDia() {

		LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();
		LocalDateTime fimDoDia = LocalDate.now().atTime(LocalTime.MAX);

		List<Pedido> pedidosDoDia = pedidoRepository.findByDataPedidoBetween(inicioDoDia, fimDoDia);

		BigDecimal totalVendidoDia = calcularTotalVendido(pedidosDoDia);
		Long quantidadePedidosDia = (long) pedidosDoDia.size();
		List<ItemMaisVendidoResponse> itensMaisVendidos = calcularItensMaisVendidos(pedidosDoDia);
		Map<StatusPedido, Long> pedidosPorStatus = calcularPedidosPorStatus(pedidosDoDia);

		return new DashboardResponse(totalVendidoDia, quantidadePedidosDia, itensMaisVendidos, pedidosPorStatus);
	}

	private BigDecimal calcularTotalVendido(List<Pedido> pedidos) {
		BigDecimal total = BigDecimal.ZERO;
		for (Pedido pedido : pedidos) {
			total = total.add(pedido.getTotal());
		}
		return total;
	}

	private Map<StatusPedido, Long> calcularPedidosPorStatus(List<Pedido> pedidos) {
		Map<StatusPedido, Long> contagem = new LinkedHashMap<>();

		// garante que todos os status apareçam no resultado, mesmo com contagem zero
		for (StatusPedido status : StatusPedido.values()) {
			contagem.put(status, 0L);
		}

		for (Pedido pedido : pedidos) {
			StatusPedido status = pedido.getStatus();
			contagem.put(status, contagem.get(status) + 1);
		}

		return contagem;
	}

	private List<ItemMaisVendidoResponse> calcularItensMaisVendidos(List<Pedido> pedidos) {

		// produtoId -> quantidade total vendida
		Map<Long, Integer> quantidadePorProduto = new HashMap<>();
		// produtoId -> nome do produto (guardado à parte pra nao precisar buscar de novo no repository)
		Map<Long, String> nomePorProduto = new HashMap<>();

		for (Pedido pedido : pedidos) {
			for (ItemPedido item : pedido.getItens()) {
				Produto produto = item.getProduto();
				Long produtoId = produto.getId();

				int quantidadeAtual = quantidadePorProduto.getOrDefault(produtoId, 0);
				quantidadePorProduto.put(produtoId, quantidadeAtual + item.getQuantidade());
				nomePorProduto.put(produtoId, produto.getNome());
			}
		}

		List<ItemMaisVendidoResponse> itensMaisVendidos = new ArrayList<>();
		for (Map.Entry<Long, Integer> entry : quantidadePorProduto.entrySet()) {
			Long produtoId = entry.getKey();
			Integer quantidadeVendida = entry.getValue();
			String nomeProduto = nomePorProduto.get(produtoId);
			itensMaisVendidos.add(new ItemMaisVendidoResponse(produtoId, nomeProduto, quantidadeVendida));
		}

		itensMaisVendidos.sort(Comparator.comparing(ItemMaisVendidoResponse::getQuantidadeVendida).reversed());

		if (itensMaisVendidos.size() > LIMITE_ITENS_MAIS_VENDIDOS) {
			return itensMaisVendidos.subList(0, LIMITE_ITENS_MAIS_VENDIDOS);
		}
		return itensMaisVendidos;
	}
}
