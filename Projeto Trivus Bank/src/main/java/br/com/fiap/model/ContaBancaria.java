package br.com.fiap.model;

public class ContaBancaria {

    private int contaId;
    private int usuarioId;
    private String agencia;
    private String numero;

    public ContaBancaria() {
    }

    public ContaBancaria(int contaId, int usuarioId, String agencia, String numero) {
        this.contaId = contaId;
        this.usuarioId = usuarioId;
        this.agencia = agencia;
        this.numero = numero;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "contaId=" + contaId +
                ", usuarioId=" + usuarioId +
                ", agencia='" + agencia + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
