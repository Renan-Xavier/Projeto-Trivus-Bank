public class UsuarioFintech {
    // Atributos
    private String nomeCompleto;
    private String cpf;
    private double rendaMensal;

    // Construtor padrão
    public UsuarioFintech() {
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

    public double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    // Construtor com parâmetros
    public UsuarioFintech(String nomeCompleto, String cpf, double rendaMensal) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
    }

    // Métodos
    public void validarDocumentos() {
        System.out.println("Validando CPF: " + cpf);
    }

    public void atualizarRenda() {
        System.out.println("Confirmação de renda do usuário: " + nomeCompleto);
    }
}
