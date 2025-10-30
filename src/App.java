import conexao.Conexao;
import views.FrmLoginFuncionario;

import java.sql.Connection;
import javax.swing.*;

public class App {
    public static void main(String[] args){
        try{
            Connection conn = Conexao.conectar();
            System.out.println("Banco criado e conectado com sucesso!");
            conn.close();

        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> new FrmLoginFuncionario());
    }
}
