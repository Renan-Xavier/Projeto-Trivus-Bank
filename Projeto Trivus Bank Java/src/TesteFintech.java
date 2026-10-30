public class TesteFintech {
    public static void main(String[] args) {


        ContaCorrente minhaConta = new ContaCorrente("23345", "32324-9", 1500.00, 500.00);
        TransferenciaPix pix = new TransferenciaPix("email@pix.com", 300.00, "2026-03-05 19:00");

        System.out.println("teste cc ");


        if (minhaConta.getSaldo() >= pix.getValorTransferencia()) {
            minhaConta.setSaldo(minhaConta.getSaldo() - pix.getValorTransferencia());
            System.out.println("PIX realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para PIX.");
        }

        minhaConta.consultarSaldo();

        minhaConta.usarChequeEspecial();

        System.out.println("\n teste usuário ");

        UsuarioFintech meuUsuario = new UsuarioFintech("Rafael", "132.232.332-24", 0.0);
        meuUsuario.validarDocumentos();
    }
}