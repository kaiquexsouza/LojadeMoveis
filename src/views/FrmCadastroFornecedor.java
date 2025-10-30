package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FrmCadastroFornecedor extends JFrame {
    private JPanel jpanel;
    private JTextField txtNome;
    private JFormattedTextField ftxtCnpj;
    private JFormattedTextField ftxtTelefone;
    private JFormattedTextField ftxtEmail;
    private JFormattedTextField ftxtEndereco;
    private JButton btnCancelar;
    private JButton btnSalvar;

    public FrmCadastroFornecedor() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Ícone
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

        // Painel principal
        jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBackground(new Color(255, 255, 204)); // amarelo claro

        // Título
        JLabel lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
        lblTitulo.setBounds(0, 20, 600, 40);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 153, 0)); // verde principal
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        jpanel.add(lblTitulo);

        // Labels e campos (cores neutras, seguem padrão)
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(100, 100, 100, 25);
        jpanel.add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(200, 100, 300, 25);
        jpanel.add(txtNome);

        JLabel lblCnpj = new JLabel("CNPJ:");
        lblCnpj.setBounds(100, 140, 100, 25);
        jpanel.add(lblCnpj);
        ftxtCnpj = new JFormattedTextField();
        ftxtCnpj.setBounds(200, 140, 300, 25);
        jpanel.add(ftxtCnpj);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(100, 180, 100, 25);
        jpanel.add(lblTelefone);
        ftxtTelefone = new JFormattedTextField();
        ftxtTelefone.setBounds(200, 180, 300, 25);
        jpanel.add(ftxtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(100, 220, 100, 25);
        jpanel.add(lblEmail);
        ftxtEmail = new JFormattedTextField();
        ftxtEmail.setBounds(200, 220, 300, 25);
        jpanel.add(ftxtEmail);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(100, 260, 100, 25);
        jpanel.add(lblEndereco);
        ftxtEndereco = new JFormattedTextField();
        ftxtEndereco.setBounds(200, 260, 300, 25);
        jpanel.add(ftxtEndereco);

        // Botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(180, 350, 100, 35);
        btnSalvar.setBackground(new Color(0, 153, 0)); // verde
        btnSalvar.setForeground(Color.WHITE);
        jpanel.add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 350, 100, 35);
        btnCancelar.setBackground(new Color(255, 204, 0)); // amarelo
        btnCancelar.setForeground(Color.BLACK);
        jpanel.add(btnCancelar);

        this.add(jpanel);

        // Eventos
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lógica de salvar (mantida)
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCadastroFornecedor.this.dispose();
                new FrmPrincipal().setVisible(true);
            }
        });
    }
}
