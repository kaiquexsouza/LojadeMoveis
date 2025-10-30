package views;

import repositorio.FuncionarioRepository;
import util.FormUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrmLoginFuncionario extends JFrame {
    private JTextField ftxtUsuario;
    private JPasswordField pfSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    private JButton btnSair;
    private JPanel jpanel;


    public FrmLoginFuncionario() {
        this.setTitle("Login Funcionario");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/Images/LogoCasasGoias_128x128.png");
        this.setIconImage(icon.getImage());

        this.add(jpanel);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] campos = {ftxtUsuario, pfSenha};

                if(FormUtil.hasEmpty(campos)) {
                    JOptionPane.showMessageDialog(FrmLoginFuncionario.this,
                            "Algo está faltando",
                            "Erro ao logar", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String usuario = ftxtUsuario.getText();
                String senha = String.valueOf(pfSenha.getPassword());

                try{
                    boolean existe = FuncionarioRepository.verificarLogin(usuario, senha);

                    if (existe) {
                        JOptionPane.showMessageDialog(FrmLoginFuncionario.this,
                                "Logado com sucesso",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        FormUtil.cleanJTexts(campos);
                    } else {
                        JOptionPane.showMessageDialog(FrmLoginFuncionario.this,
                                "Usuário ou senha incorretos",
                                "Erro ao logar", JOptionPane.WARNING_MESSAGE);
                        FormUtil.cleanJTexts(campos);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FrmLoginFuncionario.this,
                            "Falha ao acessar o banco: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLoginFuncionario.this.dispose();
            }
        });
//        btnCadastrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
    }
}
