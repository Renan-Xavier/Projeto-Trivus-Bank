package br.com.fiap.model;

import java.math.BigDecimal;

public class DashboardResumo {

    private String nomeCompleto;
    private BigDecimal saldoAtual;
    private BigDecimal saldoCofrinho;
    private String descricaoUltimaDespesa;
    private BigDecimal valorUltimaDespesa;
    private String descricaoUltimoInvestimento;
    private BigDecimal valorUltimoInvestimento;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public BigDecimal getSaldoCofrinho() {
        return saldoCofrinho;
    }

    public void setSaldoCofrinho(BigDecimal saldoCofrinho) {
        this.saldoCofrinho = saldoCofrinho;
    }

    public String getDescricaoUltimaDespesa() {
        return descricaoUltimaDespesa;
    }

    public void setDescricaoUltimaDespesa(String descricaoUltimaDespesa) {
        this.descricaoUltimaDespesa = descricaoUltimaDespesa;
    }

    public BigDecimal getValorUltimaDespesa() {
        return valorUltimaDespesa;
    }

    public void setValorUltimaDespesa(BigDecimal valorUltimaDespesa) {
        this.valorUltimaDespesa = valorUltimaDespesa;
    }

    public String getDescricaoUltimoInvestimento() {
        return descricaoUltimoInvestimento;
    }

    public void setDescricaoUltimoInvestimento(String descricaoUltimoInvestimento) {
        this.descricaoUltimoInvestimento = descricaoUltimoInvestimento;
    }

    public BigDecimal getValorUltimoInvestimento() {
        return valorUltimoInvestimento;
    }

    public void setValorUltimoInvestimento(BigDecimal valorUltimoInvestimento) {
        this.valorUltimoInvestimento = valorUltimoInvestimento;
    }

    @Override
    public String toString() {
        return "DashboardResumo{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", saldoAtual=" + saldoAtual +
                ", saldoCofrinho=" + saldoCofrinho +
                ", descricaoUltimaDespesa='" + descricaoUltimaDespesa + '\'' +
                ", valorUltimaDespesa=" + valorUltimaDespesa +
                ", descricaoUltimoInvestimento='" + descricaoUltimoInvestimento + '\'' +
                ", valorUltimoInvestimento=" + valorUltimoInvestimento +
                '}';
    }
}
