public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    // Construtor da Subclasse
    public ContaCorrente(String agencia, String numero, double saldo, double limiteChequeEspecial) {
        super(agencia, numero, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public void usarChequeEspecial() {
        System.out.println("Usando cheque especial. Limite disponível: " + limiteChequeEspecial);
    }
}