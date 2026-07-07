package br.com.hamburgueria_local.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.dto.request.UsuarioRequest;
import br.com.hamburgueria_local.dto.response.UsuarioResponse;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;
import br.com.hamburgueria_local.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponse adicionaUsuario(Usuario usuario) {
        String emailNormalizado = usuario.getEmail().trim().toLowerCase();

        if (repository.existsByEmailIgnoreCase(emailNormalizado)) {
            throw new RuntimeException("Já existe usuário cadastrado com este e-mail.");
        }

        boolean primeiroUsuario = repository.count() == 0;

        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(emailNormalizado);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setPerfil(primeiroUsuario ? PerfilUsuario.ADMIN : PerfilUsuario.CLIENTE);
        usuario.setAtivo(true);

        Usuario salvo = repository.save(usuario);
        return new UsuarioResponse(salvo);
    }

    public List<UsuarioResponse> listarAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(UsuarioResponse::new)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario user = buscarEntidadePorId(id);
        return new UsuarioResponse(user);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest dto) {
        Usuario existente = buscarEntidadePorId(id);

        String emailNormalizado = dto.getEmail().trim().toLowerCase();
        repository.findByEmailIgnoreCase(emailNormalizado)
                .filter(usuario -> !usuario.getId().equals(id))
                .ifPresent(usuario -> {
                    throw new RuntimeException("Já existe outro usuário com este e-mail.");
                });

        existente.setNome(dto.getNome().trim());
        existente.setEmail(emailNormalizado);

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            existente.setSenha(encoder.encode(dto.getSenha()));
        }

        if (dto.getPerfil() != null) {
            existente.setPerfil(dto.getPerfil());
        }

        Usuario atualizado = repository.save(existente);
        return new UsuarioResponse(atualizado);
    }

    public void inativar(Long id) {
        Usuario user = buscarEntidadePorId(id);
        user.setAtivo(false);
        repository.save(user);
    }

    private Usuario buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
