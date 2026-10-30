package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.ContaBancaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public void insert(ContaBancaria conta) {
        String sql = "INSERT INTO ContaBancaria (conta_id, usuario_id, numero_agencia, numero_conta) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, conta.getContaId());
            stmt.setInt(2, conta.getUsuarioId());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumero());
            stmt.executeUpdate();

            System.out.println("Conta cadastrada com sucesso via INSERT! ID=" + conta.getContaId());
        } catch (SQLException e) {
            if (e.getErrorCode() == 2291) {
                System.err.println("Erro ao realizar o cadastro efetivo da conta: usuario_id "
                        + conta.getUsuarioId() + " nao existe na tabela Usuario.");
            } else {
                System.err.println("Erro ao realizar o cadastro efetivo da conta: " + e.getMessage());
            }
        }
    }

    public List<ContaBancaria> getAll() {
        List<ContaBancaria> contas = new ArrayList<ContaBancaria>();
        String sql = "SELECT conta_id, usuario_id, numero_agencia, numero_conta FROM ContaBancaria ORDER BY conta_id";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContaBancaria conta = new ContaBancaria();
                conta.setContaId(rs.getInt("conta_id"));
                conta.setUsuarioId(rs.getInt("usuario_id"));
                conta.setAgencia(rs.getString("numero_agencia"));
                conta.setNumero(rs.getString("numero_conta"));
                contas.add(conta);
            }
        } catch (SQLException e) {
            System.err.println("Erro na consulta das contas bancarias: " + e.getMessage());
        }

        return contas;
    }
}
