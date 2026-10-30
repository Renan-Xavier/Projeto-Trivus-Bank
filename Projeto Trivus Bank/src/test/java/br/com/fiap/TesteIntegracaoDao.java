package br.com.fiap;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.dao.DespesaDAO;
import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.ContaBancaria;
import br.com.fiap.model.Despesa;
import br.com.fiap.model.Receita;
import br.com.fiap.model.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TesteIntegracaoDao extends TestCase {

    private static final ContaDAO CONTA_DAO = new ContaDAO();
    private static final ReceitaDAO RECEITA_DAO = new ReceitaDAO();
    private static final DespesaDAO DESPESA_DAO = new DespesaDAO();
    private static final UsuarioDAO USUARIO_DAO = new UsuarioDAO();
    private static int sequence;

    public static Test suite() {
        return new TestSuite(TesteIntegracaoDao.class);
    }

    public void testContaInsertAndGetAll() {
        int usuarioId = nextBaseId();
        ensureUsuarioFixture(usuarioId);

        List<ContaBancaria> contasInseridas = new ArrayList<ContaBancaria>();
        for (int i = 0; i < 5; i++) {
            ContaBancaria conta = new ContaBancaria(
                    usuarioId + i + 1,
                    usuarioId,
                    "000" + i,
                    "TRIVUS-CT-" + (usuarioId + i + 1));
            CONTA_DAO.insert(conta);
            contasInseridas.add(conta);
        }

        List<ContaBancaria> contasDoUsuario = new ArrayList<ContaBancaria>();
        for (ContaBancaria conta : CONTA_DAO.getAll()) {
            if (conta.getUsuarioId() == usuarioId) {
                contasDoUsuario.add(conta);
            }
        }

        assertEquals("Devem existir 5 contas para o usuario criado no teste.", 5, contasDoUsuario.size());
        for (ContaBancaria contaEsperada : contasInseridas) {
            assertTrue(
                    "A conta inserida deve ser retornada pelo getAll.",
                    containsConta(contasDoUsuario, contaEsperada));
        }
    }

    public void testReceitaInsertAndGetAllByUsuario() {
        int usuarioId = nextBaseId();
        ensureUsuarioFixture(usuarioId);

        List<Receita> receitasInseridas = new ArrayList<Receita>();
        for (int i = 0; i < 5; i++) {
            Receita receita = new Receita(
                    usuarioId + i + 1,
                    usuarioId,
                    "Receita teste " + i,
                    new BigDecimal("100.00").add(new BigDecimal(i)),
                    LocalDate.now().minusDays(i));
            RECEITA_DAO.insert(receita);
            receitasInseridas.add(receita);
        }

        List<Receita> receitasConsultadas = RECEITA_DAO.getAllByUsuarioOrderByDataDesc(usuarioId);

        assertEquals("Devem existir 5 receitas para o usuario criado no teste.", 5, receitasConsultadas.size());
        for (Receita receitaEsperada : receitasInseridas) {
            assertTrue(
                    "A receita inserida deve ser retornada na consulta por usuario.",
                    containsReceita(receitasConsultadas, receitaEsperada));
        }
    }

    public void testDespesaInsertAndGetAllByUsuario() {
        int usuarioId = nextBaseId();
        ensureUsuarioFixture(usuarioId);

        List<Despesa> despesasInseridas = new ArrayList<Despesa>();
        for (int i = 0; i < 5; i++) {
            Despesa despesa = new Despesa(
                    usuarioId + i + 1,
                    usuarioId,
                    "Despesa teste " + i,
                    new BigDecimal("50.00").add(new BigDecimal(i)),
                    LocalDate.now().minusDays(i));
            DESPESA_DAO.insert(despesa);
            despesasInseridas.add(despesa);
        }

        List<Despesa> despesasConsultadas = DESPESA_DAO.getAllByUsuarioOrderByDataDesc(usuarioId);

        assertEquals("Devem existir 5 despesas para o usuario criado no teste.", 5, despesasConsultadas.size());
        for (Despesa despesaEsperada : despesasInseridas) {
            assertTrue(
                    "A despesa inserida deve ser retornada na consulta por usuario.",
                    containsDespesa(despesasConsultadas, despesaEsperada));
        }
    }

    private static void ensureUsuarioFixture(int usuarioId) {
        Optional<Usuario> usuarioExistente = USUARIO_DAO.getById(usuarioId);
        if (usuarioExistente.isPresent()) {
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);
        usuario.setNomeCompleto("Usuario Teste " + usuarioId);
        usuario.setCpf(String.format("%011d", usuarioId));
        usuario.setSenha("teste123");
        usuario.setSaldoAtual(new BigDecimal("1000.00"));
        usuario.setSaldoCofrinho(new BigDecimal("100.00"));
        usuario.setPerfilConhecimento("INTERMEDIARIO");
        USUARIO_DAO.insert(usuario);

        assertTrue(
                "O usuario de apoio do teste precisa existir para satisfazer as chaves estrangeiras.",
                USUARIO_DAO.getById(usuarioId).isPresent());
    }

    private static int nextBaseId() {
        long valor = (System.currentTimeMillis() + sequence++) % 1000000000L;
        return (int) valor;
    }

    private static boolean containsConta(List<ContaBancaria> contas, ContaBancaria esperada) {
        for (ContaBancaria conta : contas) {
            if (conta.getContaId() == esperada.getContaId()
                    && conta.getUsuarioId() == esperada.getUsuarioId()
                    && conta.getAgencia().equals(esperada.getAgencia())
                    && conta.getNumero().equals(esperada.getNumero())) {
                return true;
            }
        }

        return false;
    }

    private static boolean containsReceita(List<Receita> receitas, Receita esperada) {
        for (Receita receita : receitas) {
            if (receita.getReceitaId() == esperada.getReceitaId()
                    && receita.getUsuarioId() == esperada.getUsuarioId()
                    && receita.getDescricao().equals(esperada.getDescricao())
                    && receita.getValor().compareTo(esperada.getValor()) == 0
                    && receita.getData().equals(esperada.getData())) {
                return true;
            }
        }

        return false;
    }

    private static boolean containsDespesa(List<Despesa> despesas, Despesa esperada) {
        for (Despesa despesa : despesas) {
            if (despesa.getDespesaId() == esperada.getDespesaId()
                    && despesa.getUsuarioId() == esperada.getUsuarioId()
                    && despesa.getDescricao().equals(esperada.getDescricao())
                    && despesa.getValor().compareTo(esperada.getValor()) == 0
                    && despesa.getData().equals(esperada.getData())) {
                return true;
            }
        }

        return false;
    }
}
