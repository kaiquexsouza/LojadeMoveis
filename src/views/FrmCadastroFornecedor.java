package views;

import entidades.Fornecedor;
import repositorio.FornecedorRepository;
import util.FormUtil;
import util.Validador;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;


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
        this.add(jpanel);
        configurarSelecaoAoFocar();


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

//        // Painel principal
//        jpanel = new JPanel();
//        jpanel.setLayout(null);
//        jpanel.setBackground(new Color(100, 200, 0)); // amarelo claro
//
//        // Título
//        JLabel lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
//        lblTitulo.setBounds(0, 20, 600, 40);
//        lblTitulo.setOpaque(true);
//        lblTitulo.setBackground(new Color(255, 204, 0)); // verde principal
//        lblTitulo.setForeground(Color.BLACK);
//        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
//        jpanel.add(lblTitulo);
//
//        // Labels e campos (cores neutras, seguem padrão)
//        JLabel lblNome = new JLabel("Nome:");
//        lblNome.setBounds(100, 100, 100, 25);
//        jpanel.add(lblNome);
//        lblNome.setForeground(Color.WHITE);
//        txtNome = new JTextField();
//        txtNome.setBounds(200, 100, 300, 25);
//        jpanel.add(txtNome);
//
//        JLabel lblCnpj = new JLabel("CNPJ:");
//        lblCnpj.setBounds(100, 140, 100, 25);
//        jpanel.add(lblCnpj);
//        lblCnpj.setForeground(Color.WHITE);
//        ftxtCnpj = new JFormattedTextField();
//        ftxtCnpj.setBounds(200, 140, 300, 25);
//        jpanel.add(ftxtCnpj);
//
//        JLabel lblTelefone = new JLabel("Telefone:");
//        lblTelefone.setBounds(100, 180, 100, 25);
//        lblTelefone.setForeground(Color.WHITE);
//        jpanel.add(lblTelefone);
//        ftxtTelefone = new JFormattedTextField();
//        ftxtTelefone.setBounds(200, 180, 300, 25);
//        jpanel.add(ftxtTelefone);
//
//        JLabel lblEmail = new JLabel("Email:");
//        lblEmail.setBounds(100, 220, 100, 25);
//        lblEmail.setForeground(Color.WHITE);
//        jpanel.add(lblEmail);
//        ftxtEmail = new JFormattedTextField();
//        ftxtEmail.setBounds(200, 220, 300, 25);
//        jpanel.add(ftxtEmail);
//
//        JLabel lblEndereco = new JLabel("Endereço:");
//        lblEndereco.setBounds(100, 260, 100, 25);
//        lblEndereco.setForeground(Color.WHITE);
//        jpanel.add(lblEndereco);
//        ftxtEndereco = new JFormattedTextField();
//        ftxtEndereco.setBounds(200, 260, 300, 25);
//        jpanel.add(ftxtEndereco);
//
//        // Botões
//        btnSalvar = new JButton("Salvar");
//        btnSalvar.setBounds(180, 350, 100, 35);
//        btnSalvar.setBackground(new Color(0, 153, 0)); // verde
//        btnSalvar.setForeground(Color.WHITE);
//        jpanel.add(btnSalvar);
//
//        btnCancelar = new JButton("Cancelar");
//        btnCancelar.setBounds(320, 350, 100, 35);
//        btnCancelar.setBackground(new Color(255, 200, 0));
//        btnCancelar.setForeground(Color.WHITE);
//        jpanel.add(btnCancelar);


        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] campos = {txtNome, ftxtCnpj, ftxtTelefone, ftxtEmail, ftxtEndereco};

                if(FormUtil.hasEmpty(campos)){
                    JOptionPane.showMessageDialog(FrmCadastroFornecedor.this,
                            "Preencha todos os campos",
                            "Erro ao salvar",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setNome(txtNome.getText());
                    fornecedor.setCnpj(FormUtil.limparMascara(ftxtCnpj.getText()) );
                    fornecedor.setTelefone(FormUtil.limparMascara(ftxtTelefone.getText()) );
                    fornecedor.setEmail(ftxtEmail.getText());
                    fornecedor.setEndereco(ftxtEndereco.getText());

                    if(!validarCampos(fornecedor)){
                        return;
                    }

                    try{
                        FornecedorRepository.inserir(fornecedor);
                        FormUtil.cleanJTexts(campos);
                        JOptionPane.showMessageDialog(
                                FrmCadastroFornecedor.this,
                                "Salvo com Sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro ao cadastrar fornecedor: " + ex.getMessage()
                        );
                    }
                }
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

    public boolean validarCampos(Fornecedor f) {

        if (!Validador.validarCNPJ(f.getCnpj())) {
            JOptionPane.showMessageDialog(null, "CNPJ inválido! Deve conter 14 dígitos.");
            return false;
        }

        if (!Validador.validarTelefone(f.getTelefone())) {
            JOptionPane.showMessageDialog(null, "Telefone inválido! Deve conter 11 dígitos.");
            return false;
        }

        if (!Validador.validarEmail(f.getEmail())) {
            JOptionPane.showMessageDialog(null, "E-mail inválido!");
            return false;
        }

        return true;
    }

    public void createUIComponents() {
        try {
            MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
            maskCnpj.setPlaceholderCharacter('_');
            maskCnpj.setValueContainsLiteralCharacters(false);
            ftxtCnpj = new JFormattedTextField(maskCnpj);
            ftxtCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);

            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.setPlaceholderCharacter('_');
            maskTelefone.setValueContainsLiteralCharacters(false);
            ftxtTelefone = new JFormattedTextField(maskTelefone);
            ftxtTelefone.setFocusLostBehavior(JFormattedTextField.PERSIST);

            ftxtEmail = new JFormattedTextField();
            ftxtEndereco = new JFormattedTextField();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarSelecaoAoFocar() {

        FocusListener selecionarTudo = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    ((JFormattedTextField)e.getComponent()).selectAll();
                });
            }
        };

        ftxtCnpj.addFocusListener(selecionarTudo);
        ftxtTelefone.addFocusListener(selecionarTudo);
    }
}
