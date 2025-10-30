package views;

import conexao.Conexao;
import entidades.Funcionario;
import repositorio.FuncionarioRepository;
import util.FormUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

public class FrmCadastroFuncionario extends JFrame{
    private JFormattedTextField ftxtUsuario;
    private JPasswordField pfSenha;
    private JPasswordField pfConfirmaSenha;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JPanel jpanel;


    public FrmCadastroFuncionario() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        URL iconURL = getClass().getResource("/Images/LogoCasasGoias_128x128.png");
        if (iconURL != null) {
            Image icon = Toolkit.getDefaultToolkit().getImage(iconURL);
            this.setIconImage(icon);

            try {
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    Taskbar.getTaskbar().setIconImage(icon);
                }
            } catch (Exception e) {
                System.out.println("Não foi possível definir o ícone da Dock: " + e.getMessage());
            }
        } else {
            System.err.println("Ícone não encontrado no repositório");
        }

        this.add(jpanel);
        this.setVisible(true);


        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCadastroFuncionario.this.dispose();
                new FrmLoginFuncionario().setVisible(true);
            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] campos = {ftxtUsuario, pfSenha, pfConfirmaSenha};

                if(FormUtil.hasEmpty(campos)) {
                    JOptionPane.showMessageDialog(FrmCadastroFuncionario.this,
                            "Algo está faltando",
                            "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String usuario = ftxtUsuario.getText();
                String senha = String.valueOf(pfSenha.getPassword());
                String senhaConfirmada = String.valueOf(pfConfirmaSenha.getPassword());

                try{
                    boolean validaSenha = senha.equals(senhaConfirmada);
                    boolean consultaUsuario = FuncionarioRepository.consultaUsuario(usuario);

                    if(!validaSenha) {
                        JOptionPane.showMessageDialog(FrmCadastroFuncionario.this,
                                "As senhas digitadas devem ser iguais",
                                "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    } else if(consultaUsuario){
                        JOptionPane.showMessageDialog(FrmCadastroFuncionario.this,
                                "Usuário já existente",
                                "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    } else{
                        Funcionario funcionario = new Funcionario(usuario, senha);
                        boolean inserirFuncionario = FuncionarioRepository.inserir(funcionario);
                        JOptionPane.showMessageDialog(FrmCadastroFuncionario.this,
                                "Usuário registrado com sucesso",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        FrmCadastroFuncionario.this.dispose();
                        new FrmLoginFuncionario().setVisible(true);
                    }
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(FrmCadastroFuncionario.this,
                            "Falha ao acessar o banco: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
