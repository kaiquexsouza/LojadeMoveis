package views;

import repositorio.FuncionarioRepository;
import util.FormUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.SQLException;
import java.net.URL;


public class FrmLoginFuncionario extends JFrame {
    private JTextField ftxtUsuario;
    private JPasswordField pfSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    private JButton btnSair;
    private JPanel desktop;

    public FrmLoginFuncionario() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(desktop);
        this.setVisible(true);

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

//        desktop.setBackground(new Color(100, 200, 0));
//        btnEntrar.setBackground(new Color(0, 153, 0));
//        btnEntrar.setForeground(Color.BLACK);
//        btnCadastrar.setBackground(new Color(255, 200, 0));
//        btnCadastrar.setForeground(Color.BLACK);
//        btnSair.setBackground(new Color(255, 200, 0));
//        btnSair.setForeground(Color.BLACK);


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

                        FrmLoginFuncionario.this.dispose();
                        new FrmPrincipal().setVisible(true);
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
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLoginFuncionario.this.dispose();
                new FrmCadastroFuncionario().setVisible(true);
            }
        });
    }
}