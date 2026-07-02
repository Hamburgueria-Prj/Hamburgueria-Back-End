package br.com.hamburgueria_local.dto.request;

import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
	@NotBlank private String nome;
    @NotBlank @Email(message = "Email invalido") private String email;
    @NotBlank @Size(min = 6, message = "Senha deve ter ao menos 6 caracteres")
    private String senha;
    @NotNull private PerfilUsuario perfil;

    public UsuarioRequest() {
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }


}
