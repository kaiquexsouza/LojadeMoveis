package conexao;

import java.sql.*;

public class Conexao {
    private static final String pathURL = "jdbc:sqlite:loja_moveis.db";

    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(pathURL);
        criarTabelas(con);
        return con;
    }

    private static void criarTabelas(Connection con) throws SQLException {
        String sqlMovel = """
            CREATE TABLE IF NOT EXISTS movel (
                id_movel      INTEGER PRIMARY KEY AUTOINCREMENT,
                id_fornecedor INTEGER NOT NULL,
                cor           TEXT NOT NULL,
                descricao     TEXT NOT NULL,
                material      TEXT NOT NULL,
                altura        REAL NOT NULL,
                largura       REAL NOT NULL,
                comprimento   REAL NOT NULL,
                preco         REAL NOT NULL,
                tipo          TEXT DEFAULT 'RACK' CHECK (tipo IN ('RACK', 'CADEIRA', 'MESA')),
                FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id_fornecedor) ON DELETE CASCADE ON UPDATE CASCADE
            )
        """;

        String sqlArmario = """
            CREATE TABLE IF NOT EXISTS armario (
                id_armario     INTEGER PRIMARY KEY AUTOINCREMENT,
                id_movel       INTEGER NOT NULL,
                numero_portas  INTEGER NOT NULL,
                FOREIGN KEY (id_movel) REFERENCES movel(id_movel) ON DELETE CASCADE
            )
        """;

        String sqlFornecedor = """
            CREATE TABLE IF NOT EXISTS fornecedor (
                id_fornecedor  INTEGER PRIMARY KEY AUTOINCREMENT,
                nome           TEXT NOT NULL,
                cnpj           TEXT NOT NULL,
                telefone       TEXT NOT NULL,
                email          TEXT NOT NULL,
                endereco       TEXT NOT NULL
            )
        """;

        String sqlFuncionario = """
            CREATE TABLE IF NOT EXISTS funcionario (
                id_funcionario INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario        TEXT UNIQUE NOT NULL,
                senha          TEXT NOT NULL
            )
        """;

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sqlMovel);
            stmt.executeUpdate(sqlArmario);
            stmt.executeUpdate(sqlFornecedor);
            stmt.executeUpdate(sqlFuncionario);
        }
    }

    public static boolean executarSql(String sql) {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao executar SQL: " + e.getMessage());
            return false;
        }
    }

    public static ResultSet executarQuery(String sql, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
}