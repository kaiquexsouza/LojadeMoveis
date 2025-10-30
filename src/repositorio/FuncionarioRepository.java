package repositorio;

import conexao.Conexao;
import entidades.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FuncionarioRepository {
    public static boolean inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (usuario, senha) VALUES (" +
                "'" + funcionario.getUsuario() + "', " +
                "'" + funcionario.getSenha() + "'" +
                ")";
        return Conexao.executarSql(sql);
    }

    public static int lastId() throws SQLException {
        Connection conn = Conexao.conectar();
        String sql = "SELECT MAX(id) AS id FROM funcionario";

        ResultSet rs = Conexao.executarQuery(sql, conn);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }

        conn.close();
        return id;
    }

    public static boolean verificarLogin(String usuario, String senha) throws SQLException {
        Connection conn = Conexao.conectar();
        String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();

        boolean existe = rs.next();

        rs.close();
        stmt.close();
        conn.close();

        return existe;
    }
}
