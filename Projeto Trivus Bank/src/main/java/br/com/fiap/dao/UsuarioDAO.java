package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDAO {

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO Usuario "
                + "(usuario_id, nome_completo, cpf, senha, saldo_atual, saldo_cofrinho, perfil_conhecimento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getUsuarioId());
            stmt.setString(2, usuario.getNomeCompleto());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getSenha());
            stmt.setBigDecimal(5, usuario.getSaldoAtual());
            stmt.setBigDecimal(6, usuario.getSaldoCofrinho());
            stmt.setString(7, usuario.getPerfilConhecimento());
            stmt.executeUpdate();

            System.out.println("Usuario cadastrado com sucesso! ID=" + usuario.getUsuarioId());
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                System.err.println("Erro ao cadastrar usuario: ID ou CPF ja existente. ID=" + usuario.getUsuarioId());
            } else {
                System.err.println("Erro ao cadastrar usuario: " + e.getMessage());
            }
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome_completo = ?, senha = ?, perfil_conhecimento = ? "
                + "WHERE usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getPerfilConhecimento());
            stmt.setInt(4, usuario.getUsuarioId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuario atualizado com sucesso! ID=" + usuario.getUsuarioId());
            } else {
                System.out.println("Nenhum usuario encontrado para atualizar com ID=" + usuario.getUsuarioId());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuario: " + e.getMessage());
        }
    }

    public Optional<Usuario> getById(int usuarioId) {
        String sql = "SELECT usuario_id, nome_completo, cpf, senha, saldo_atual, saldo_cofrinho, perfil_conhecimento "
                + "FROM Usuario WHERE usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt("usuario_id"));
                    usuario.setNomeCompleto(rs.getString("nome_completo"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setSaldoAtual(rs.getBigDecimal("saldo_atual"));
                    usuario.setSaldoCofrinho(rs.getBigDecimal("saldo_cofrinho"));
                    usuario.setPerfilConhecimento(rs.getString("perfil_conhecimento"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar usuario: " + e.getMessage());
        }

        return Optional.empty();
    }
}
