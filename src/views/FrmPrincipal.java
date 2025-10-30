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
