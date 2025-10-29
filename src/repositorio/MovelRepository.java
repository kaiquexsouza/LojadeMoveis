package repositorio;

import conexao.Conexao;
import entidades.Armario;
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
        String sql = "INSERT INTO movel (cor, descricao, material, altura, largura, comprimento, preco, tipo, fornecedor) " +
                "VALUES ('" + movel.getCor() + "', '" + movel.getDescricao() + "', '" + movel.getMaterial() + "', " +
                movel.getAltura() + ", " + movel.getLargura() + ", " + movel.getComprimento() + ", " +
                movel.getPreco() + ", '" + movel.getTipoMovel().name() + "', '" + movel.getFornecedor() + "')";

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

    public static List<Movel> listarPorTipo(TipoMovel tipo) throws SQLException {
        try {
            String sql = """
                SELECT id, cor, descricao, material, altura, largura, comprimento, preco, tipo
                FROM movel
                INNER JOIN fornecedor ON movel.id = fornecedor.id
                WHERE tipo = '""" + tipo.name() + "'";

            Connection con = Conexao.conectar();
            ResultSet rs = Conexao.executarQuery(sql, con);

            List<Movel> moveis = new ArrayList<>();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
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

            con.close();
            return moveis;

        } catch (Exception e) {
            System.out.println("Erro ao listar móveis por tipo: " + e.getMessage());
        }
        return null;
    }

    public static List<Movel> listar() {
        try {
            String sql = "SELECT id, cor, descricao, material, altura, largura, comprimento, preco, tipo, fornecedor FROM movel";

            Connection con = Conexao.conectar();
            ResultSet rs = Conexao.executarQuery(sql, con);

            List<Movel> moveis = new ArrayList<>();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
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

            con.close();
            return moveis;

        } catch (Exception e) {
            System.out.println("Erro ao listar móveis: " + e.getMessage());
        }
        return null;
    }
}
