package br.com.fiap.model;

import java.math.BigDecimal;

public class Usuario {

    private int usuarioId;
    private String nomeCompleto;
    private String cpf;
    private String senha;
    private BigDecimal saldoAtual;
    private BigDecimal saldoCofrinho;
    private String perfilConhecimento;

    public Usuario() {
    }

    public Usuario(int usuarioId, String nomeCompleto, String cpf, String senha,
                   BigDecimal saldoAtual, BigDecimal saldoCofrinho, String perfilConhecimento) {
        this.usuarioId = usuarioId;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.senha = senha;
        this.saldoAtual = saldoAtual;
        this.saldoCofrinho = saldoCofrinho;
        this.perfilConhecimento = perfilConhecimento;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getPerfilConhecimento() {
        return perfilConhecimento;
    }

    public void setPerfilConhecimento(String perfilConhecimento) {
        this.perfilConhecimento = perfilConhecimento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", cpf='" + cpf + '\'' +
                ", saldoAtual=" + saldoAtual +
                ", saldoCofrinho=" + saldoCofrinho +
                ", perfilConhecimento='" + perfilConhecimento + '\'' +
                '}';
    }
}
