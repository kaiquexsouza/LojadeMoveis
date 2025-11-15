package repositorio;

import conexao.Conexao;
import entidades.Fornecedor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class FornecedorRepository {
    public static boolean inserir(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor (nome, cnpj, telefone, email, endereco) VALUES ('"
                + fornecedor.getNome() + "', '"
                + fornecedor.getCnpj() + "', '"
                + fornecedor.getTelefone() + "', '"
                + fornecedor.getEmail() + "', '"
                + fornecedor.getEndereco() + "')";
        return Conexao.executarSql(sql);
    }

    public static int lastId() throws SQLException {
        Connection conn = Conexao.conectar();
        String sql = "SELECT MAX(id_fornecedor) AS id_fornecedor FROM fornecedor";

        ResultSet rs = Conexao.executarQuery(sql, conn);
        int id_fornecedor = 0;
        if (rs.next()) {
            id_fornecedor = rs.getInt("id_fornecedor");
        }

        conn.close();
        return id_fornecedor;
    }

    public static List<Fornecedor> listarPorNome(String nome) throws SQLException {
        String sql = """
        SELECT 
            id_fornecedor,
            nome,
            substr(cnpj,1,2) || '.' || 
            substr(cnpj,3,3) || '.' || 
            substr(cnpj,6,3) || '/' || 
            substr(cnpj,9,4) || '-' || 
            substr(cnpj,13,2) AS cnpj,
            telefone,
            email,
            endereco
        FROM fornecedor
        WHERE nome LIKE ?;
    """;

        Connection con = Conexao.conectar();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nome + "%");

        ResultSet rs = stmt.executeQuery();

        List<Fornecedor> fornecedores = new ArrayList<>();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor(
                    rs.getString("nome"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
            );
            fornecedores.add(fornecedor);
        }

        con.close();
        return fornecedores;
    }


    public static List<Fornecedor> listar() throws SQLException {
        String sql = """
        SELECT
            id_fornecedor,
            nome,
            substr(cnpj,1,2) || '.' || 
            substr(cnpj,3,3) || '.' || 
            substr(cnpj,6,3) || '/' || 
            substr(cnpj,9,4) || '-' || 
            substr(cnpj,13,2) AS cnpj,
            telefone,
            email,
            endereco
        FROM fornecedor;
    """;

        Connection con = Conexao.conectar();
        ResultSet rs = Conexao.executarQuery(sql, con);

        List<Fornecedor> fornecedores = new ArrayList<>();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor(
                    rs.getString("nome"),
                    rs.getString("cnpj"),     // já vem formatado (00.000.000/0000-00)
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
            );
            fornecedores.add(fornecedor);
        }

        con.close();
        return fornecedores;
    }
}