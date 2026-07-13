package br.com.hamburgueria_local.entities;

import br.com.hamburgueria_local.enums.CategoriaProduto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_produtos_nome_categoria", columnNames = {"nome", "categoria"})
})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProduto categoria;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Lob
    @Column(name = "imagem_base64", columnDefinition = "LONGTEXT")
    private String imagemBase64;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.ativo = ativo;
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria, Boolean ativo, String imagemBase64) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.ativo = ativo;
        this.imagemBase64 = imagemBase64;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }


    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }
}