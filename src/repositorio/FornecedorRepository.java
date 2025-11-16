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
            '(' || substr(telefone, 1, 2) || ') ' ||
            substr(telefone, 3, 5) || '-' ||
            substr(telefone, 8, 4) AS telefone,
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
                    rs.getInt("id_fornecedor"),
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
            '(' || substr(telefone, 1, 2) || ') ' ||
            substr(telefone, 3, 5) || '-' ||
            substr(telefone, 8, 4) AS telefone,
            email,
            endereco
        FROM fornecedor;
    """;

        Connection con = Conexao.conectar();
        ResultSet rs = Conexao.executarQuery(sql, con);

        List<Fornecedor> fornecedores = new ArrayList<>();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor(
                    rs.getInt("id_fornecedor"),
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

    public static Fornecedor buscarPorCnpj(String cnpj) throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE cnpj = ?";
        Connection con = Conexao.conectar();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cnpj);

        ResultSet rs = stmt.executeQuery();
        Fornecedor f = null;

        if (rs.next()) {
            f = new Fornecedor(
                    rs.getInt("id_fornecedor"),
                    rs.getString("nome"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
            );
        }

        con.close();
        return f;
    }
}