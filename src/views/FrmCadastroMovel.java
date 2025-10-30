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
    private JCheckBox checkRack;
    private JCheckBox checkCadeira;
    private JCheckBox checkMesa;
    private JCheckBox checkBanco;
    private JTextField txtFornecedor;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public FrmCadastroMovel() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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

        this.add(jpanel);


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
