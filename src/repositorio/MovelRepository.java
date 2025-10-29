package repositorio;

import conexao.Conexao;
import entidades.Fornecedor;
import entidades.Movel;
import entidades.enumeracao.TipoMovel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovelRepository {
    public static boolean inserir(Movel movel) throws SQLException {
        String sql = "INSERT INTO movel (cor, descricao, material, altura, largura, comprimento, preco, tipo, id_fornecedor) " +
                "VALUES ('" + movel.getCor() + "', '" + movel.getDescricao() + "', '" + movel.getMaterial() + "', " +
                movel.getAltura() + ", " + movel.getLargura() + ", " + movel.getComprimento() + ", " +
                movel.getPreco() + ", '" + movel.getTipoMovel().name() + "', " + movel.getFornecedor().getId_fornecedor() + ")";

        return Conexao.executarSql(sql);
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
