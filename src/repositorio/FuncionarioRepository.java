package repositorio;

import conexao.Conexao;
import entidades.Fornecedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    return null;
    }
}
