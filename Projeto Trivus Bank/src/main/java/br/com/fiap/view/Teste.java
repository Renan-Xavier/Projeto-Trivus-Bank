package br.com.fiap.view;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.dao.DashboardDAO;
import br.com.fiap.dao.DespesaDAO;
import br.com.fiap.dao.InvestimentoDAO;
import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.ContaBancaria;
import br.com.fiap.model.DashboardResumo;
import br.com.fiap.model.Despesa;
import br.com.fiap.model.Investimento;
import br.com.fiap.model.Receita;
import br.com.fiap.model.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Teste {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final UsuarioDAO USUARIO_DAO = new UsuarioDAO();
    private static final ContaDAO CONTA_DAO = new ContaDAO();
    private static final ReceitaDAO RECEITA_DAO = new ReceitaDAO();
    private static final DespesaDAO DESPESA_DAO = new DespesaDAO();
    private static final InvestimentoDAO INVESTIMENTO_DAO = new InvestimentoDAO();
    private static final DashboardDAO DASHBOARD_DAO = new DashboardDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenuPrincipal();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    menuCadastro();
                    break;
                case 2:
                    menuAlteracao();
                    break;
                case 3:
                    menuConsultaSimples();
                    break;
                case 4:
                    menuConsultaOrdenada();
                    break;
                case 5:
                    consultarDashboard();
                    break;
                case 0:
                    System.out.println("Encerrando sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("1. Cadastro");
        System.out.println("2. Alteracao");
        System.out.println("3. Consulta simples");
        System.out.println("4. Consultas ordenadas");
        System.out.println("5. Dashboard");
        System.out.println("0. Sair");
    }

    private static void menuCadastro() {
        System.out.println();
        System.out.println("1. Cadastrar usuario");
        System.out.println("2. Cadastrar conta bancaria");
        System.out.println("3. Cadastrar receita");
        System.out.println("4. Cadastrar despesa");
        System.out.println("5. Cadastrar investimento");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha uma opcao: ");

        switch (opcao) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                cadastrarConta();
                break;
            case 3:
                cadastrarReceita();
                break;
            case 4:
                cadastrarDespesa();
                break;
            case 5:
                cadastrarInvestimento();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void menuAlteracao() {
        System.out.println();
        System.out.println("1. Alterar usuario");
        System.out.println("2. Alterar receita");
        System.out.println("3. Alterar despesa");
        System.out.println("4. Alterar investimento");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha uma opcao: ");

        switch (opcao) {
            case 1:
                alterarUsuario();
                break;
            case 2:
                alterarReceita();
                break;
            case 3:
                alterarDespesa();
                break;
            case 4:
                alterarInvestimento();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void menuConsultaSimples() {
        System.out.println();
        System.out.println("1. Consultar usuario por ID");
        System.out.println("2. Consultar receita por ID e usuario");
        System.out.println("3. Consultar despesa por ID e usuario");
        System.out.println("4. Consultar investimento por ID e usuario");
        System.out.println("5. Consultar todas as contas");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha uma opcao: ");

        switch (opcao) {
            case 1:
                consultarUsuario();
                break;
            case 2:
                consultarReceita();
                break;
            case 3:
                consultarDespesa();
                break;
            case 4:
                consultarInvestimento();
                break;
            case 5:
                consultarContas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void menuConsultaOrdenada() {
        System.out.println();
        System.out.println("1. Listar despesas por usuario");
        System.out.println("2. Listar investimentos por usuario");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha uma opcao: ");

        switch (opcao) {
            case 1:
                listarDespesasPorUsuario();
                break;
            case 2:
                listarInvestimentosPorUsuario();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void cadastrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(lerInteiro("ID do usuario: "));
        usuario.setNomeCompleto(lerTexto("Nome completo: "));
        usuario.setCpf(lerTexto("CPF: "));
        usuario.setSenha(lerTexto("Senha: "));
        usuario.setSaldoAtual(lerBigDecimal("Saldo atual: "));
        usuario.setSaldoCofrinho(lerBigDecimal("Saldo cofrinho: "));
        usuario.setPerfilConhecimento(lerTexto("Perfil de conhecimento: "));
        USUARIO_DAO.insert(usuario);
    }

    private static void cadastrarConta() {
        ContaBancaria conta = new ContaBancaria();
        conta.setContaId(lerInteiro("ID da conta: "));
        conta.setUsuarioId(lerInteiro("ID do usuario: "));
        conta.setAgencia(lerTexto("Numero da agencia: "));
        conta.setNumero(lerTexto("Numero da conta: "));
        CONTA_DAO.insert(conta);
    }

    private static void cadastrarReceita() {
        Receita receita = new Receita();
        receita.setReceitaId(lerInteiro("ID da receita: "));
        receita.setUsuarioId(lerInteiro("ID do usuario: "));
        receita.setDescricao(lerTexto("Descricao da receita: "));
        receita.setValor(lerBigDecimal("Valor da receita: "));
        receita.setData(lerData("Data da receita (dd/MM/yyyy): "));
        RECEITA_DAO.insert(receita);
    }

    private static void cadastrarDespesa() {
        Despesa despesa = new Despesa();
        despesa.setDespesaId(lerInteiro("ID da despesa: "));
        despesa.setUsuarioId(lerInteiro("ID do usuario: "));
        despesa.setDescricao(lerTexto("Descricao da despesa: "));
        despesa.setValor(lerBigDecimal("Valor da despesa: "));
        despesa.setData(lerData("Data da despesa (dd/MM/yyyy): "));
        DESPESA_DAO.insert(despesa);
    }

    private static void cadastrarInvestimento() {
        Investimento investimento = new Investimento();
        investimento.setInvestimentoId(lerInteiro("ID do investimento: "));
        investimento.setUsuarioId(lerInteiro("ID do usuario: "));
        investimento.setDescricao(lerTexto("Descricao do investimento: "));
        investimento.setValor(lerBigDecimal("Valor do investimento: "));
        investimento.setData(lerData("Data do investimento (dd/MM/yyyy): "));
        INVESTIMENTO_DAO.insert(investimento);
    }

    private static void alterarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(lerInteiro("ID do usuario: "));
        usuario.setNomeCompleto(lerTexto("Novo nome: "));
        usuario.setSenha(lerTexto("Nova senha: "));
        usuario.setPerfilConhecimento(lerTexto("Novo perfil de conhecimento: "));
        USUARIO_DAO.update(usuario);
    }

    private static void alterarReceita() {
        Receita receita = new Receita();
        receita.setReceitaId(lerInteiro("ID da receita: "));
        receita.setUsuarioId(lerInteiro("ID do usuario: "));
        receita.setDescricao(lerTexto("Nova descricao: "));
        receita.setValor(lerBigDecimal("Novo valor: "));
        RECEITA_DAO.update(receita);
    }

    private static void alterarDespesa() {
        Despesa despesa = new Despesa();
        despesa.setDespesaId(lerInteiro("ID da despesa: "));
        despesa.setUsuarioId(lerInteiro("ID do usuario: "));
        despesa.setDescricao(lerTexto("Nova descricao: "));
        despesa.setValor(lerBigDecimal("Novo valor: "));
        DESPESA_DAO.update(despesa);
    }

    private static void alterarInvestimento() {
        Investimento investimento = new Investimento();
        investimento.setInvestimentoId(lerInteiro("ID do investimento: "));
        investimento.setUsuarioId(lerInteiro("ID do usuario: "));
        investimento.setDescricao(lerTexto("Nova descricao: "));
        investimento.setValor(lerBigDecimal("Novo valor: "));
        INVESTIMENTO_DAO.update(investimento);
    }

    private static void consultarUsuario() {
        int usuarioId = lerInteiro("ID do usuario: ");
        Optional<Usuario> usuario = USUARIO_DAO.getById(usuarioId);
        exibirResultado(usuario);
    }

    private static void consultarReceita() {
        int receitaId = lerInteiro("ID da receita: ");
        int usuarioId = lerInteiro("ID do usuario: ");
        Optional<Receita> receita = RECEITA_DAO.getById(receitaId, usuarioId);
        exibirResultado(receita);
    }

    private static void consultarDespesa() {
        int despesaId = lerInteiro("ID da despesa: ");
        int usuarioId = lerInteiro("ID do usuario: ");
        Optional<Despesa> despesa = DESPESA_DAO.getById(despesaId, usuarioId);
        exibirResultado(despesa);
    }

    private static void consultarInvestimento() {
        int investimentoId = lerInteiro("ID do investimento: ");
        int usuarioId = lerInteiro("ID do usuario: ");
        Optional<Investimento> investimento = INVESTIMENTO_DAO.getById(investimentoId, usuarioId);
        exibirResultado(investimento);
    }

    private static void consultarContas() {
        List<ContaBancaria> contas = CONTA_DAO.getAll();
        if (contas.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        for (ContaBancaria conta : contas) {
            System.out.println(conta);
        }
    }

    private static void listarDespesasPorUsuario() {
        int usuarioId = lerInteiro("ID do usuario: ");
        List<Despesa> despesas = DESPESA_DAO.getAllByUsuarioOrderByDataDesc(usuarioId);
        exibirLista(despesas);
    }

    private static void listarInvestimentosPorUsuario() {
        int usuarioId = lerInteiro("ID do usuario: ");
        List<Investimento> investimentos = INVESTIMENTO_DAO.getAllByUsuarioOrderByDataDesc(usuarioId);
        exibirLista(investimentos);
    }

    private static void consultarDashboard() {
        int usuarioId = lerInteiro("ID do usuario: ");
        Optional<DashboardResumo> dashboard = DASHBOARD_DAO.getByUsuarioId(usuarioId);
        exibirResultado(dashboard);
    }

    private static void exibirResultado(Optional<?> resultado) {
        if (resultado.isPresent()) {
            System.out.println(resultado.get());
        } else {
            System.out.println("Nenhum registro encontrado.");
        }
    }

    private static void exibirLista(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        for (Object item : lista) {
            System.out.println(item);
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = lerLinha().trim();

            if (valor.isEmpty()) {
                continue;
            }

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static BigDecimal lerBigDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = lerLinha().trim().replace(",", ".");

            if (valor.isEmpty()) {
                continue;
            }

            try {
                return new BigDecimal(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numerico valido.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = lerLinha().trim();

            if (valor.isEmpty()) {
                continue;
            }

            try {
                return LocalDate.parse(valor, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Digite a data no formato dd/MM/yyyy.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return lerLinha().trim();
    }

    private static String lerLinha() {
        if (!SCANNER.hasNextLine()) {
            System.out.println();
            System.out.println("Entrada finalizada. Encerrando sistema.");
            System.exit(0);
        }

        return SCANNER.nextLine();
    }
}
