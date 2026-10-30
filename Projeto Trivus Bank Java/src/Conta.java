public class Conta {
    private String agencia;
    private String numero;
    private double saldo;

    // Construtor da Superclasse
    public Conta(String agencia, String numero, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Métodos (Comportamentos)
    public void consultarSaldo() {
        System.out.println("Saldo da conta " + numero + ": R$ " + saldo);
    }
}