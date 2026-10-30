package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Investimento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvestimentoDAO {

    public void insert(Investimento investimento) {
        String sql = "INSERT INTO Investimento (investimento_id, usuario_id, ds_investimento, vl_investimento, dt_investimento) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, investimento.getInvestimentoId());
            stmt.setInt(2, investimento.getUsuarioId());
            stmt.setString(3, investimento.getDescricao());
            stmt.setBigDecimal(4, investimento.getValor());
            stmt.setDate(5, Date.valueOf(investimento.getData()));
            stmt.executeUpdate();

            System.out.println("Investimento cadastrado com sucesso! ID=" + investimento.getInvestimentoId());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar investimento: " + e.getMessage());
        }
    }

    public void update(Investimento investimento) {
        String sql = "UPDATE Investimento SET ds_investimento = ?, vl_investimento = ? "
                + "WHERE investimento_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, investimento.getDescricao());
            stmt.setBigDecimal(2, investimento.getValor());
            stmt.setInt(3, investimento.getInvestimentoId());
            stmt.setInt(4, investimento.getUsuarioId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Investimento atualizado com sucesso! ID=" + investimento.getInvestimentoId());
            } else {
                System.out.println("Nenhum investimento encontrado para atualizar.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar investimento: " + e.getMessage());
        }
    }

    public Optional<Investimento> getById(int investimentoId, int usuarioId) {
        String sql = "SELECT investimento_id, usuario_id, ds_investimento, vl_investimento, dt_investimento "
                + "FROM Investimento WHERE investimento_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, investimentoId);
            stmt.setInt(2, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Investimento investimento = new Investimento();
                    investimento.setInvestimentoId(rs.getInt("investimento_id"));
                    investimento.setUsuarioId(rs.getInt("usuario_id"));
                    investimento.setDescricao(rs.getString("ds_investimento"));
                    investimento.setValor(rs.getBigDecimal("vl_investimento"));
                    investimento.setData(rs.getDate("dt_investimento").toLocalDate());
                    return Optional.of(investimento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar investimento: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Investimento> getAllByUsuarioOrderByDataDesc(int usuarioId) {
        List<Investimento> investimentos = new ArrayList<Investimento>();
        String sql = "SELECT investimento_id, usuario_id, ds_investimento, vl_investimento, dt_investimento "
                + "FROM Investimento WHERE usuario_id = ? ORDER BY dt_investimento DESC";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Investimento investimento = new Investimento();
                    investimento.setInvestimentoId(rs.getInt("investimento_id"));
                    investimento.setUsuarioId(rs.getInt("usuario_id"));
                    investimento.setDescricao(rs.getString("ds_investimento"));
                    investimento.setValor(rs.getBigDecimal("vl_investimento"));
                    investimento.setData(rs.getDate("dt_investimento").toLocalDate());
                    investimentos.add(investimento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar investimentos: " + e.getMessage());
        }

        return investimentos;
    }
}
