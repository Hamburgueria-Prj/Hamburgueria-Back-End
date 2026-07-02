package br.com.hamburgueria_local.entities;

import br.com.hamburgueria_local.enums.PerfilUsuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @Column(nullable = false)
    private String senha; // sempre salvar com hash BCrypt
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private PerfilUsuario perfil;
    @Column(nullable = false)
    private Boolean ativo = true;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, PerfilUsuario perfil, Boolean ativo, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
	
	

}
