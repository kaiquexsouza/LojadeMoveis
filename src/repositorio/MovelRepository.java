package repositorio;

import conexao.Conexao;
import entidades.Fornecedor;
import entidades.Movel;
import entidades.enumeracao.TipoMovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovelRepository {
    public static boolean inserir(Movel movel) throws SQLException {
        String sql = """
        INSERT INTO movel 
        (id_fornecedor, cor, descricao, material, altura, largura, comprimento, preco, tipo)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        Connection con = Conexao.conectar();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, movel.getFornecedor().getId_fornecedor());
        stmt.setString(2, movel.getCor());
        stmt.setString(3, movel.getDescricao());
        stmt.setString(4, movel.getMaterial());
        stmt.setFloat(5, movel.getAltura());
        stmt.setFloat(6, movel.getLargura());
        stmt.setFloat(7, movel.getComprimento());
        stmt.setFloat(8, movel.getPreco());
        stmt.setString(9, movel.getTipoMovel().name());

        boolean ok = stmt.executeUpdate() > 0;

        stmt.close();
        con.close();
        return ok;
    }

    public static int lastId() throws SQLException {
        Connection conn = Conexao.conectar();
        String sql = "SELECT MAX(id) AS id FROM movel";

        ResultSet rs = Conexao.executarQuery(sql, conn);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
        }

        conn.close();
        return id;
    }

    public static List<Movel> listar(TipoMovel tipo) throws SQLException {
        List<Movel> moveis = new ArrayList<>();
        String sql = """
        SELECT m.id, m.cor, m.descricao, m.material, m.altura, m.largura, m.comprimento, m.preco, m.tipo,
               f.id AS id_fornecedor, f.nome, f.cnpj, f.telefone, f.email, f.endereco
        FROM movel m
        INNER JOIN fornecedor f ON m.id_fornecedor = f.id_fornecedor
    """;

        if (tipo != null) {
            sql += " WHERE m.tipo = '" + tipo.name() + "'";
        }

        try (Connection con = Conexao.conectar();
             ResultSet rs = Conexao.executarQuery(sql, con)) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id_fornecedor"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );

                Movel movel = new Movel(
                        rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getString("descricao"),
                        rs.getString("material"),
                        rs.getFloat("altura"),
                        rs.getFloat("largura"),
                        rs.getFloat("comprimento"),
                        rs.getFloat("preco"),
                        TipoMovel.valueOf(rs.getString("tipo")),
                        fornecedor
                );
                moveis.add(movel);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar móveis: " + e.getMessage());
        }

        return moveis;
    }
}
