package views;

import entidades.Movel;
import repositorio.MovelRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FrmPesquisaMovel extends JFrame {
    private JPanel jpanel;
    private JTextField txtPesquisa;
    private JButton btnPesquisar;
    private JScrollPane scrolMoveis;
    private JTable tblMoveis;
    private JButton btnCancelar;

    public FrmPesquisaMovel() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1000, 800);
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

        DefaultTableModel tb = new DefaultTableModel();
        tb.addColumn("Descrição");
        tb.addColumn("Cor");
        tb.addColumn("Material");
        tb.addColumn("Altura");
        tb.addColumn("Largura");
        tb.addColumn("Comprimento");
        tb.addColumn("Preco");
        tb.addColumn("Fornecedor");
        tblMoveis.setModel(tb);

        tblMoveis.getColumnModel().getColumn(0).setPreferredWidth(250);
        tblMoveis.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblMoveis.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblMoveis.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblMoveis.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblMoveis.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblMoveis.getColumnModel().getColumn(6).setPreferredWidth(90);
        tblMoveis.getColumnModel().getColumn(7).setPreferredWidth(160);



        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblMoveis.getModel();
                String tipo = txtPesquisa.getText();


            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPesquisaMovel.this.dispose();
                new FrmPrincipal().setVisible(true);
            }
        });
    }
}
