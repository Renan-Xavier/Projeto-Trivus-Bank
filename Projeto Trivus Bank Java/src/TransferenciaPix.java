public class TransferenciaPix {
    // Atributos
    private String chaveDestino;
    private double valorTransferencia;
    private String dataHora;

    // Construtor padrão
    public TransferenciaPix() {
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    // Construtor com parâmetros
    public TransferenciaPix(String chaveDestino, double valorTransferencia, String dataHora) {
        this.chaveDestino = chaveDestino;
        this.valorTransferencia = valorTransferencia;
        this.dataHora = dataHora;
    }

    // Métodos
    public void enviarPix() {
        System.out.println("Digite o valor: " + valorTransferencia + "Digite a chave pix desejada: " + chaveDestino);
    }

    public void gerarComprovantePix() {
        System.out.println("Comprovante do Pix: " + dataHora);
    }
}