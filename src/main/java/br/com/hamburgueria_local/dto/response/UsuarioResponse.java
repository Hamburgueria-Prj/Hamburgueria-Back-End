package br.com.hamburgueria_local.dto.response;

import java.time.LocalDateTime;

import br.com.hamburgueria_local.entities.Usuario;
import br.com.hamburgueria_local.enums.PerfilUsuario;

public class UsuarioResponse {
		   
	   
	    private String nome;
	    
	    private String email;
	    
	    private String senha; 
	   
	    private PerfilUsuario perfil;
	   
	    private Boolean ativo = true;
	    
	    private LocalDateTime dataCadastro = LocalDateTime.now();


	    public UsuarioResponse() {
			// TODO Auto-generated constructor stub
		}


	    public UsuarioResponse(Usuario usuario) {
			this.nome = usuario.getNome();
			this.email = usuario.getEmail();
			this.senha = usuario.getSenha();
			this.perfil = usuario.getPerfil();
			this.ativo = usuario.getAtivo();
			this.dataCadastro = usuario.getDataCadastro();
		}

		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getSenha() {
			return senha;
		}


		public void setSenha(String senha) {
			this.senha = senha;
		}


		public PerfilUsuario getPerfil() {
			return perfil;
		}


		public void setPerfil(PerfilUsuario perfil) {
			this.perfil = perfil;
		}


		public Boolean getAtivo() {
			return ativo;
		}


		public void setAtivo(Boolean ativo) {
			this.ativo = ativo;
		}


		public LocalDateTime getDataCadastro() {
			return dataCadastro;
		}


		public void setDataCadastro(LocalDateTime dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
	    
	    
}
