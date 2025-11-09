package views;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FrmPrincipal extends JFrame{
    private JButton btnPesquisaMovel;
    private JButton btnCadastroMovel;
    private JButton btnCadastroFornecedor;
    private JButton btnPesquisaFornecedor;
    private JPanel jpanel;


    public FrmPrincipal(){
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize((new Dimension(1000, 1000)));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
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

//        jpanel.setBackground(new Color(100, 200, 0));
//
//        btnPesquisaMovel.setBackground(new Color(0, 153, 0));
//        btnPesquisaMovel.setForeground(Color.WHITE);
//        btnCadastroMovel.setBackground(new Color(0, 153, 0));
//        btnCadastroMovel.setForeground(Color.WHITE);
//        btnCadastroFornecedor.setBackground(new Color(0, 153, 0));
//        btnCadastroFornecedor.setForeground(Color.WHITE);
//        btnPesquisaFornecedor.setBackground(new Color(0, 153, 0));
//        btnPesquisaFornecedor.setForeground(Color.WHITE);
//
//        URL logoURL = getClass().getResource("/Images/LogoGeralSemFundo.png");
//        if (logoURL != null) {
//            ImageIcon logoIcon = new ImageIcon(logoURL);
//            JLabel lblLogo = new JLabel(logoIcon, SwingConstants.CENTER);
//
//            lblLogo.setBounds(0, 20, 1000, logoIcon.getIconHeight());
//            jpanel.add(lblLogo);
//        } else {
//            System.err.println("Imagem da logo geral não encontrada.");
//        }


        btnCadastroMovel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmPrincipal.this.dispose();
                new FrmCadastroMovel().setVisible(true);
            }
        });
        btnPesquisaMovel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmPrincipal.this.dispose();
                new FrmPesquisaMovel().setVisible(true);
            }
        });
        btnCadastroFornecedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmPrincipal.this.dispose();
                new FrmCadastroFornecedor().setVisible(true);
            }
        });
        btnPesquisaFornecedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmPrincipal.this.dispose();
                new FrmPesquisaFornecedor().setVisible(true);
            }
        });
    }
}