package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Receita;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceitaDAO {

    public void insert(Receita receita) {
        String sql = "INSERT INTO Receita (receita_id, usuario_id, ds_receita, vl_receita, dt_receita) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, receita.getReceitaId());
            stmt.setInt(2, receita.getUsuarioId());
            stmt.setString(3, receita.getDescricao());
            stmt.setBigDecimal(4, receita.getValor());
            stmt.setDate(5, Date.valueOf(receita.getData()));
            stmt.executeUpdate();

            System.out.println("Receita cadastrada com sucesso! ID=" + receita.getReceitaId());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar receita: " + e.getMessage());
        }
    }

    public void update(Receita receita) {
        String sql = "UPDATE Receita SET ds_receita = ?, vl_receita = ? WHERE receita_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, receita.getDescricao());
            stmt.setBigDecimal(2, receita.getValor());
            stmt.setInt(3, receita.getReceitaId());
            stmt.setInt(4, receita.getUsuarioId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Receita atualizada com sucesso! ID=" + receita.getReceitaId());
            } else {
                System.out.println("Nenhuma receita encontrada para atualizar.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar receita: " + e.getMessage());
        }
    }

    public Optional<Receita> getById(int receitaId, int usuarioId) {
        String sql = "SELECT receita_id, usuario_id, ds_receita, vl_receita, dt_receita "
                + "FROM Receita WHERE receita_id = ? AND usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, receitaId);
            stmt.setInt(2, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Receita receita = new Receita();
                    receita.setReceitaId(rs.getInt("receita_id"));
                    receita.setUsuarioId(rs.getInt("usuario_id"));
                    receita.setDescricao(rs.getString("ds_receita"));
                    receita.setValor(rs.getBigDecimal("vl_receita"));
                    receita.setData(rs.getDate("dt_receita").toLocalDate());
                    return Optional.of(receita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar receita: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Receita> getAllByUsuarioOrderByDataDesc(int usuarioId) {
        List<Receita> receitas = new ArrayList<Receita>();
        String sql = "SELECT receita_id, usuario_id, ds_receita, vl_receita, dt_receita "
                + "FROM Receita WHERE usuario_id = ? ORDER BY dt_receita DESC";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Receita receita = new Receita();
                    receita.setReceitaId(rs.getInt("receita_id"));
                    receita.setUsuarioId(rs.getInt("usuario_id"));
                    receita.setDescricao(rs.getString("ds_receita"));
                    receita.setValor(rs.getBigDecimal("vl_receita"));
                    receita.setData(rs.getDate("dt_receita").toLocalDate());
                    receitas.add(receita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar receitas: " + e.getMessage());
        }

        return receitas;
    }
}
