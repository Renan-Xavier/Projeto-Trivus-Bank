package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Despesa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DespesaDAO {

    public void insert(Despesa despesa) {
        String sql = "INSERT INTO Despesa (despesa_id, usuario_id, ds_despesa, vl_despesa, dt_despesa) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, despesa.getDespesaId());
            stmt.setInt(2, despesa.getUsuarioId());
            stmt.setString(3, despesa.getDescricao());
            stmt.setBigDecimal(4, despesa.getValor());
            stmt.setDate(5, Date.valueOf(despesa.getData()));
            stmt.executeUpdate();

            System.out.println("Despesa cadastrada com sucesso! ID=" + despesa.getDespesaId());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar despesa: " + e.getMessage());
        }
    }

    public void update(Despesa despesa) {
        String sql = "UPDATE Despesa SET ds_despesa = ?, vl_despesa = ? WHERE despesa_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, despesa.getDescricao());
            stmt.setBigDecimal(2, despesa.getValor());
            stmt.setInt(3, despesa.getDespesaId());
            stmt.setInt(4, despesa.getUsuarioId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Despesa atualizada com sucesso! ID=" + despesa.getDespesaId());
            } else {
                System.out.println("Nenhuma despesa encontrada para atualizar.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar despesa: " + e.getMessage());
        }
    }

    public Optional<Despesa> getById(int despesaId, int usuarioId) {
        String sql = "SELECT despesa_id, usuario_id, ds_despesa, vl_despesa, dt_despesa "
                + "FROM Despesa WHERE despesa_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, despesaId);
            stmt.setInt(2, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Despesa despesa = new Despesa();
                    despesa.setDespesaId(rs.getInt("despesa_id"));
                    despesa.setUsuarioId(rs.getInt("usuario_id"));
                    despesa.setDescricao(rs.getString("ds_despesa"));
                    despesa.setValor(rs.getBigDecimal("vl_despesa"));
                    despesa.setData(rs.getDate("dt_despesa").toLocalDate());
                    return Optional.of(despesa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar despesa: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Despesa> getAllByUsuarioOrderByDataDesc(int usuarioId) {
        List<Despesa> despesas = new ArrayList<Despesa>();
        String sql = "SELECT despesa_id, usuario_id, ds_despesa, vl_despesa, dt_despesa "
                + "FROM Despesa WHERE usuario_id = ? ORDER BY dt_despesa DESC";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Despesa despesa = new Despesa();
                    despesa.setDespesaId(rs.getInt("despesa_id"));
                    despesa.setUsuarioId(rs.getInt("usuario_id"));
                    despesa.setDescricao(rs.getString("ds_despesa"));
                    despesa.setValor(rs.getBigDecimal("vl_despesa"));
                    despesa.setData(rs.getDate("dt_despesa").toLocalDate());
                    despesas.add(despesa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar despesas: " + e.getMessage());
        }

        return despesas;
    }
}
