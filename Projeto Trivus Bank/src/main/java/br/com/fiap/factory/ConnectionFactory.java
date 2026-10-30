package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DEFAULT_URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String DEFAULT_USER = "RM566997";
    private static final String DEFAULT_PASSWORD = "110893";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                lerConfiguracao("TRIVUS_DB_URL", "trivus.db.url", DEFAULT_URL),
                lerConfiguracao("TRIVUS_DB_USER", "trivus.db.user", DEFAULT_USER),
                lerConfiguracao("TRIVUS_DB_PASSWORD", "trivus.db.password", DEFAULT_PASSWORD));
    }

    private static String lerConfiguracao(String variavelAmbiente, String propriedadeSistema, String valorPadrao) {
        String valor = System.getenv(variavelAmbiente);
        if (valor != null && !valor.isBlank()) {
            return valor;
        }

        valor = System.getProperty(propriedadeSistema);
        if (valor != null && !valor.isBlank()) {
            return valor;
        }

        return valorPadrao;
    }
}
