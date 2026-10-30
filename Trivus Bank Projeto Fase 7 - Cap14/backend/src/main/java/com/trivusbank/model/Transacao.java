package com.trivusbank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq")
    @SequenceGenerator(name = "transacao_seq", sequenceName = "seq_transacao", allocationSize = 1)
    private Long id;

    @NotNull(message = "O usuário é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @NotNull(message = "O tipo é obrigatório")
    @Column(nullable = false, length = 10)
    private String tipo;

    @Positive(message = "O valor deve ser positivo")
    @NotNull(message = "O valor é obrigatório")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(length = 255)
    private String descricao;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataTransacao = LocalDateTime.now();
        ativo = true;
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public Transacao() {
    }

    public Transacao(Usuario usuario, Categoria categoria, String tipo, BigDecimal valor, String descricao) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.ativo = true;
    }

    public Transacao(Long id, Usuario usuario, Categoria categoria, String tipo, BigDecimal valor,
                     String descricao, LocalDateTime dataTransacao, LocalDateTime dataCriacao,
                     LocalDateTime dataAtualizacao, Boolean ativo) {
        this.id = id;
        this.usuario = usuario;
        this.categoria = categoria;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataTransacao = dataTransacao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", usuario=" + usuario.getNome() +
                ", categoria=" + categoria.getNome() +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", ativo=" + ativo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        if (id != null ? !id.equals(transacao.id) : transacao.id != null) return false;
        return dataTransacao != null ? dataTransacao.equals(transacao.dataTransacao) : transacao.dataTransacao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dataTransacao != null ? dataTransacao.hashCode() : 0);
        return result;
    }
}

