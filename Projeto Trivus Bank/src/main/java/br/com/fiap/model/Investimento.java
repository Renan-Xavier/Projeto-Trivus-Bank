package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento {

    private int investimentoId;
    private int usuarioId;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public Investimento() {
    }

    public Investimento(int investimentoId, int usuarioId, String descricao, BigDecimal valor, LocalDate data) {
        this.investimentoId = investimentoId;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public int getInvestimentoId() {
        return investimentoId;
    }

    public void setInvestimentoId(int investimentoId) {
        this.investimentoId = investimentoId;
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
        return "Investimento{" +
                "investimentoId=" + investimentoId +
                ", usuarioId=" + usuarioId +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
