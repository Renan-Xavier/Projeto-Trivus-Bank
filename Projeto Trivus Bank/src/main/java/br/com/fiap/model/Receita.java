package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receita {

    private int receitaId;
    private int usuarioId;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public Receita() {
    }

    public Receita(int receitaId, int usuarioId, String descricao, BigDecimal valor, LocalDate data) {
        this.receitaId = receitaId;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public int getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(int receitaId) {
        this.receitaId = receitaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "receitaId=" + receitaId +
                ", usuarioId=" + usuarioId +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
