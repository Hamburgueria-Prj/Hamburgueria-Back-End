package br.com.hamburgueria_local.services;

import br.com.hamburgueria_local.dto.request.ClienteRequest;
import br.com.hamburgueria_local.dto.response.ClienteResponse;
import br.com.hamburgueria_local.entities.Cliente;
import br.com.hamburgueria_local.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponse salvar(ClienteRequest dto) {
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setAtivo(true);

        Cliente salvo = repository.save(cliente);

        return new ClienteResponse(salvo);
    }

    public List<ClienteResponse> listarAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(ClienteResponse::new)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        return new ClienteResponse(cliente);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest dto) {
        Cliente existente = buscarEntidadePorId(id);

        existente.setNome(dto.getNome());
        existente.setTelefone(dto.getTelefone());
        existente.setEndereco(dto.getEndereco());

        Cliente atualizado = repository.save(existente);

        return new ClienteResponse(atualizado);
    }

    public void inativar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        cliente.setAtivo(false);
        repository.save(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}