package br.com.hamburgueria_local.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hamburgueria_local.dto.request.AuthLoginRequest;
import br.com.hamburgueria_local.dto.request.AuthRegisterRequest;
import br.com.hamburgueria_local.dto.response.AuthResponse;
import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;
import br.com.hamburgueria_local.repositories.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AuthResponse registrar(AuthRegisterRequest request) {
        String emailNormalizado = normalizarEmail(request.getEmail());

        if (usuarioRepository.existsByEmailIgnoreCase(emailNormalizado)) {
            throw new RuntimeException("Já existe usuário cadastrado com este e-mail.");
        }

        boolean primeiroUsuario = usuarioRepository.count() == 0;

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome().trim());
        usuario.setEmail(emailNormalizado);
        usuario.setSenha(encoder.encode(request.getSenha()));
        usuario.setPerfil(primeiroUsuario ? PerfilUsuario.ADMIN : PerfilUsuario.CLIENTE);
        usuario.setAtivo(true);

        Usuario salvo = usuarioRepository.save(usuario);

        String mensagem = primeiroUsuario
                ? "Primeiro usuário cadastrado como ADMIN."
                : "Usuário cadastrado como CLIENTE.";

        return new AuthResponse(salvo, mensagem);
    }

    public AuthResponse login(AuthLoginRequest request) {
        String emailNormalizado = normalizarEmail(request.getEmail());

        Usuario usuario = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(emailNormalizado)
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos."));

        if (!senhaConfere(request.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        return new AuthResponse(usuario, "Login realizado com sucesso.");
    }

    private boolean senhaConfere(String senhaDigitada, String senhaSalva) {
        if (senhaSalva == null || senhaSalva.isBlank()) {
            return false;
        }

        if (senhaSalva.startsWith("$2a$") || senhaSalva.startsWith("$2b$") || senhaSalva.startsWith("$2y$")) {
            return encoder.matches(senhaDigitada, senhaSalva);
        }

        return senhaDigitada.equals(senhaSalva);
    }

    private String normalizarEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }
}
