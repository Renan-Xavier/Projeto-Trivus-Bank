package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.DashboardResumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DashboardDAO {

    public Optional<DashboardResumo> getByUsuarioId(int usuarioId) {
        String sql = "SELECT "
                + "u.nome_completo, "
                + "u.saldo_atual, "
                + "u.saldo_cofrinho, "
                + "d.ds_despesa AS descricao_ultima_despesa, "
                + "d.vl_despesa AS valor_ultima_despesa, "
                + "i.ds_investimento AS descricao_ultimo_investimento, "
                + "i.vl_investimento AS valor_ultimo_investimento "
                + "FROM Usuario u "
                + "LEFT JOIN ( "
                + "    SELECT usuario_id, ds_despesa, vl_despesa, "
                + "           ROW_NUMBER() OVER(PARTITION BY usuario_id ORDER BY dt_despesa DESC) as rn "
                + "    FROM Despesa "
                + ") d ON u.usuario_id = d.usuario_id AND d.rn = 1 "
                + "LEFT JOIN ( "
                + "    SELECT usuario_id, ds_investimento, vl_investimento, "
                + "           ROW_NUMBER() OVER(PARTITION BY usuario_id ORDER BY dt_investimento DESC) as rn "
                + "    FROM Investimento "
                + ") i ON u.usuario_id = i.usuario_id AND i.rn = 1 "
                + "WHERE u.usuario_id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DashboardResumo resumo = new DashboardResumo();
                    resumo.setNomeCompleto(rs.getString("nome_completo"));
                    resumo.setSaldoAtual(rs.getBigDecimal("saldo_atual"));
                    resumo.setSaldoCofrinho(rs.getBigDecimal("saldo_cofrinho"));
                    resumo.setDescricaoUltimaDespesa(rs.getString("descricao_ultima_despesa"));
                    resumo.setValorUltimaDespesa(rs.getBigDecimal("valor_ultima_despesa"));
                    resumo.setDescricaoUltimoInvestimento(rs.getString("descricao_ultimo_investimento"));
                    resumo.setValorUltimoInvestimento(rs.getBigDecimal("valor_ultimo_investimento"));
                    return Optional.of(resumo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar dashboard: " + e.getMessage());
        }

        return Optional.empty();
    }
}
