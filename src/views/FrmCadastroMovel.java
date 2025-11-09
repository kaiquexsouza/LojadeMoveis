package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FrmCadastroMovel extends JFrame {
    private JTextField txtDescricao;
    private JTextField txtCor;
    private JTextField txtMaterial;
    private JFormattedTextField ftxtAltura;
    private JFormattedTextField ftxtLargura;
    private JFormattedTextField ftxtComprimento;
    private JFormattedTextField ftxtPreco;
    private JPanel jpanel;
    private JTextField txtFornecedor;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JComboBox tipoMovel;

    public FrmCadastroMovel() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(jpanel);
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

        String[] tiposMovel = {"Rack", "Cadeira", "Mesa", "Banco"};

        tipoMovel.addItem("");
        for(String movel :  tiposMovel) {
            tipoMovel.addItem(movel);
        }

//        jpanel = new JPanel();
//        jpanel.setLayout(null);
//        jpanel.setBackground(new Color(100, 200, 0));
//
//        JLabel lblTitulo = new JLabel("Cadastro de Móvel", SwingConstants.CENTER);
//        lblTitulo.setBounds(0, 20, 600, 40);
//        lblTitulo.setOpaque(true);
//        lblTitulo.setBackground(new Color(255, 204, 0));
//        lblTitulo.setForeground(Color.BLACK);
//        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
//        jpanel.add(lblTitulo);
//
//        JLabel lblDescricao = new JLabel("Descrição:");
//        lblDescricao.setBounds(100, 100, 100, 25);
//        lblDescricao.setForeground(Color.WHITE);
//        jpanel.add(lblDescricao);
//        txtDescricao = new JTextField();
//        txtDescricao.setBounds(200, 100, 300, 25);
//        jpanel.add(txtDescricao);
//
//        JLabel lblCor = new JLabel("Cor:");
//        lblCor.setBounds(100, 140, 100, 25);
//        lblCor.setForeground(Color.WHITE);
//        jpanel.add(lblCor);
//        txtCor = new JTextField();
//        txtCor.setBounds(200, 140, 300, 25);
//        jpanel.add(txtCor);
//
//        JLabel lblMaterial = new JLabel("Material:");
//        lblMaterial.setBounds(100, 180, 100, 25);
//        lblMaterial.setForeground(Color.WHITE);
//        jpanel.add(lblMaterial);
//        txtMaterial = new JTextField();
//        txtMaterial.setBounds(200, 180, 300, 25);
//        jpanel.add(txtMaterial);
//
//        JLabel lblAltura = new JLabel("Altura:");
//        lblAltura.setBounds(100, 220, 100, 25);
//        lblAltura.setForeground(Color.WHITE);
//        jpanel.add(lblAltura);
//        ftxtAltura = new JFormattedTextField();
//        ftxtAltura.setBounds(200, 220, 100, 25);
//        jpanel.add(ftxtAltura);
//
//        JLabel lblLargura = new JLabel("Largura:");
//        lblLargura.setBounds(320, 220, 80, 25);
//        lblLargura.setForeground(Color.WHITE);
//        jpanel.add(lblLargura);
//        ftxtLargura = new JFormattedTextField();
//        ftxtLargura.setBounds(400, 220, 100, 25);
//        jpanel.add(ftxtLargura);
//
//        JLabel lblComprimento = new JLabel("Comprimento:");
//        lblComprimento.setBounds(100, 260, 100, 25);
//        lblComprimento.setForeground(Color.WHITE);
//        jpanel.add(lblComprimento);
//        ftxtComprimento = new JFormattedTextField();
//        ftxtComprimento.setBounds(200, 260, 300, 25);
//        jpanel.add(ftxtComprimento);
//
//        JLabel lblPreco = new JLabel("Preço:");
//        lblPreco.setBounds(100, 300, 100, 25);
//        lblPreco.setForeground(Color.WHITE);
//        jpanel.add(lblPreco);
//        ftxtPreco = new JFormattedTextField();
//        ftxtPreco.setBounds(200, 300, 300, 25);
//        jpanel.add(ftxtPreco);
//
//        JLabel lblTipo = new JLabel("Tipo:");
//        lblTipo.setBounds(100, 340, 100, 25);
//        lblTipo.setForeground(Color.WHITE);
//        jpanel.add(lblTipo);
//
//        checkRack = new JCheckBox("Rack");
//        checkRack.setBounds(200, 340, 80, 25);
//        checkRack.setBackground(new Color(100, 200, 0));
//        checkRack.setForeground(Color.WHITE);
//        jpanel.add(checkRack);
//
//        checkCadeira = new JCheckBox("Cadeira");
//        checkCadeira.setBounds(280, 340, 100, 25);
//        checkCadeira.setBackground(new Color(100, 200, 0));
//        checkCadeira.setForeground(Color.WHITE);
//        jpanel.add(checkCadeira);
//
//        checkMesa = new JCheckBox("Mesa");
//        checkMesa.setBounds(380, 340, 80, 25);
//        checkMesa.setBackground(new Color(100, 200, 0));
//        checkMesa.setForeground(Color.WHITE);
//        jpanel.add(checkMesa);
//
//        checkBanco = new JCheckBox("Banco");
//        checkBanco.setBounds(460, 340, 80, 25);
//        checkBanco.setBackground(new Color(100, 200, 0));
//        checkBanco.setForeground(Color.WHITE);
//        jpanel.add(checkBanco);
//
//        JLabel lblFornecedor = new JLabel("Fornecedor:");
//        lblFornecedor.setBounds(100, 380, 100, 25);
//        lblFornecedor.setForeground(Color.WHITE);
//        jpanel.add(lblFornecedor);
//        txtFornecedor = new JTextField();
//        txtFornecedor.setBounds(200, 380, 300, 25);
//        jpanel.add(txtFornecedor);
//
//        btnSalvar = new JButton("Salvar");
//        btnSalvar.setBounds(180, 450, 100, 35);
//        btnSalvar.setBackground(new Color(0, 153, 0));
//        btnSalvar.setForeground(Color.WHITE);
//        jpanel.add(btnSalvar);
//
//        btnCancelar = new JButton("Cancelar");
//        btnCancelar.setBounds(320, 450, 100, 35);
//        btnCancelar.setBackground(new Color(255, 200, 0));
//        btnCancelar.setForeground(Color.WHITE);
//        jpanel.add(btnCancelar);



        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCadastroMovel.this.dispose();
                new FrmPrincipal().setVisible(true);
            }
        });
    }
}
