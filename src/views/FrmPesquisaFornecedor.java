package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FrmPesquisaFornecedor extends JFrame {
    private JPanel jpanel;
    private JTextField txtPesquisa;
    private JButton btnPesquisar;
    private JScrollPane scrolFornecedores;
    private JTable tblFornecedores;
    private JButton btnCancelar;

    public FrmPesquisaFornecedor() {
        this.setTitle("Casas Goiás");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(900, 900);
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
        tb.addColumn("Nome");
        tb.addColumn("CNPJ");
        tb.addColumn("telefone");
        tb.addColumn("email");
        tb.addColumn("Endereço");

        tblFornecedores.setModel(tb);

        tblFornecedores.getColumnModel().getColumn(0).setPreferredWidth(160);
        tblFornecedores.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(4).setPreferredWidth(180);



        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPesquisaFornecedor.this.dispose();
                new FrmPrincipal().setVisible(true);
            }
        });
    }
}
