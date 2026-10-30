public class CartaoCredito {
    // Atributos
    private String finalCartao;
    private double limiteAprovado;
    private boolean estaBloqueado; // true se bloqueado, false se ativo

    // Construtor padrão
    public CartaoCredito() {
    }

    public String getFinalCartao() {
        return finalCartao;
    }

    public void setFinalCartao(String finalCartao) {
        this.finalCartao = finalCartao;
    }

    public boolean isEstaBloqueado() {
        return estaBloqueado;
    }

    public void setEstaBloqueado(boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
    }

    public double getLimiteAprovado() {
        return limiteAprovado;
    }

    public void setLimiteAprovado(double limiteAprovado) {
        this.limiteAprovado = limiteAprovado;
    }

    // Construtor com parâmetros
    public CartaoCredito(String finalCartao, double limiteAprovado, boolean estaBloqueado) {
        this.finalCartao = finalCartao;
        this.limiteAprovado = limiteAprovado;
        this.estaBloqueado = estaBloqueado;
    }

    // Métodos
    public void realizarCompraOnline() {
        System.out.println("Ultimos digitos do cartão de crédito: " + finalCartao);
    }

    public void solicitarAumentoLimite() {
        System.out.println(" Limite atual: " + limiteAprovado);
    }
}