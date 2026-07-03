package br.com.hamburgueria_local.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.dto.request.PagamentoRequest;
import br.com.hamburgueria_local.dto.response.PagamentoResponse;
import br.com.hamburgueria_local.entities.Pagamento;
import br.com.hamburgueria_local.entities.Pedido;
import br.com.hamburgueria_local.enums.FormaPagamento;
import br.com.hamburgueria_local.enums.StatusPagamento;
import br.com.hamburgueria_local.repositories.PagamentoRepository;
import br.com.hamburgueria_local.repositories.PedidoRepository;

@Service
public class PagamentoService {

    @Autowired private PagamentoRepository repository;
    @Autowired private PedidoRepository pedidoRepository;

    public PagamentoResponse salvar(PagamentoRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public List<PagamentoResponse> listar() {
        return repository.findAll().stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public PagamentoResponse buscarPorId(Long id) {
        return toResponse(buscarEntidade(id));
    }

    // ---- conversoes ----
    private Pagamento toEntity(PagamentoRequest r) {
        Pagamento p = new Pagamento();

        Pedido pedido = pedidoRepository.findById(r.getPedidoId())
            .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
        p.setPedido(pedido);

        p.setFormaPagamento(r.getFormaPagamento());
        p.setValorPago(r.getValorPago());
        p.setTrocoPara(r.getTrocoPara());

        // TODO: calcular o troco aqui (ou em um metodo separado)
        // TODO: definir o statusPagamento
        // TODO: definir a dataPagamento

        return p;
    }

    private PagamentoResponse toResponse(Pagamento p) {
        PagamentoResponse res = new PagamentoResponse();
        res.setId(p.getId());
        res.setPedidoId(p.getPedido().getId());
        res.setFormaPagamento(p.getFormaPagamento());
        res.setStatusPagamento(p.getStatusPagamento());
        res.setValorPago(p.getValorPago());
        res.setTrocoPara(p.getTrocoPara());
        res.setTroco(p.getTroco());
        res.setDataPagamento(p.getDataPagamento());
        return res;
    }

    private Pagamento buscarEntidade(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));
    }
}