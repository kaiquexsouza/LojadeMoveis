import conexao.Conexao;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try{
            Connection conn = Conexao.conectar();
            System.out.println("Banco criado e conectado com sucesso!");

            String sql = "INSERT INTO funcionario (usuario, senha) VALUES ('admin','1234')";
            Conexao.executarSql(sql);

            String sqls = "INSERT INTO funcionario (usuario, senha) VALUES ('admin2','3456')";
            Conexao.executarSql(sqls);

//            String truncateRegisters = "DELETE FROM funcionario";
//            Conexao.executarSql(truncateRegisters);
//
//            String truncateIds = "DELETE FROM sqlite_sequence WHERE name = 'funcionario'";
//            Conexao.executarSql(truncateIds);

            System.out.println("Funcionario criado com sucesso!");
            conn.close();

        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
