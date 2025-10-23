package repositorio;

import conexao.Conexao;
import entidades.Armario;
import entidades.enumeracao.TipoMovel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmarioRepository {
    public static boolean inserir(Armario armario) throws SQLException {
        boolean sucessoMovel = MovelRepository.inserir(armario);
        if (!sucessoMovel) return false;

        int idMovel = MovelRepository.lastId();

        String sql = "INSERT INTO armario (numero_portas) " +
                "VALUES (" + idMovel + ", " + armario.getNumeroPortas() + ")";

        return Conexao.executarSql(sql);
    }

    public static int lastId() throws SQLException {
        Connection conn = Conexao.conectar();
        String sql = "SELECT MAX(id_movel) AS id_movel FROM armario";

        ResultSet rs = Conexao.executarQuery(sql, conn);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id_movel");
        }

        conn.close();
        return id;
    }

    public static List<Armario> listarPorTipo(TipoMovel tipo) {
        try {
            String sql = """
                    SELECT m.id, m.cor, m.descricao, m.material, m.altura, m.largura, 
                           m.comprimento, m.preco, m.tipo, a.numero_portas
                    FROM armario a
                    INNER JOIN movel m ON m.id = a.id_movel
                    WHERE m.tipo = '""" + tipo.name() + "'";

            Connection con = Conexao.conectar();
            ResultSet rs = Conexao.executarQuery(sql, con);

            List<Armario> armarios = new ArrayList<>();
            while (rs.next()) {
                Armario armario = new Armario(
                        rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getString("descricao"),
                        rs.getString("material"),
                        rs.getFloat("altura"),
                        rs.getFloat("largura"),
                        rs.getFloat("comprimento"),
                        rs.getFloat("preco"),
                        TipoMovel.valueOf(rs.getString("tipo")),
                        rs.getInt("numero_portas"));
                armarios.add(armario);
            }

            con.close();
            return armarios;

        } catch (Exception e) {
            System.out.println("Erro ao listar armários por tipo: " + e.getMessage());
        }

        return null;
    }

    public static List<Armario> listar() {
        try {
            String sql = """
                        SELECT m.id, m.cor, m.descricao, m.material, m.altura, m.largura, 
                               m.comprimento, m.preco, m.tipo, a.numero_portas
                        FROM armario a
                        INNER JOIN movel m ON m.id = a.id_movel
                    """;

            Connection con = Conexao.conectar();
            ResultSet rs = Conexao.executarQuery(sql, con);

            List<Armario> armarios = new ArrayList<>();
            while (rs.next()) {
                Armario armario = new Armario(
                        rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getString("descricao"),
                        rs.getString("material"),
                        rs.getFloat("altura"),
                        rs.getFloat("largura"),
                        rs.getFloat("comprimento"),
                        rs.getFloat("preco"),
                        TipoMovel.valueOf(rs.getString("tipo")),
                        rs.getInt("numero_portas"));
                armarios.add(armario);
            }

            con.close();
            return armarios;

        } catch (Exception e) {
            System.out.println("Erro ao listar armários: " + e.getMessage());
        }

        return null;
    }
}