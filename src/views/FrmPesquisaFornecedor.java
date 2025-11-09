package views;

import entidades.Fornecedor;
import repositorio.FornecedorRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

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
        this.setSize(1000, 800);
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

//        jpanel.setBackground(new Color(100, 200, 0));
//
//        txtPesquisa.setBackground(new Color(50, 50, 50));
//        txtPesquisa.setForeground(Color.WHITE);
//
//        btnPesquisar.setBackground(new Color(0, 153, 0));
//        btnPesquisar.setForeground(Color.WHITE);
//
//        btnCancelar.setBackground(new Color(255, 200, 0));
//        btnCancelar.setForeground(Color.WHITE);
//
//        tblFornecedores.setBackground(Color.WHITE);
//        tblFornecedores.setForeground(Color.BLACK);
//        tblFornecedores.getTableHeader().setBackground(new Color(255, 204, 0));
//        tblFornecedores.getTableHeader().setForeground(Color.BLACK);
//        scrolFornecedores.getViewport().setBackground(Color.WHITE);

        DefaultTableModel tb = new DefaultTableModel();
        tb.addColumn("Nome");
        tb.addColumn("CNPJ");
        tb.addColumn("Telefone");
        tb.addColumn("Email");
        tb.addColumn("Endereço");

        tblFornecedores.setModel(tb);

        tblFornecedores.getColumnModel().getColumn(0).setPreferredWidth(160);
        tblFornecedores.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblFornecedores.getColumnModel().getColumn(4).setPreferredWidth(180);

        updateFornecedorTable();

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFornecedorTable();
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

    private void updateFornecedorTable() {
        DefaultTableModel model = (DefaultTableModel) tblFornecedores.getModel();

        String nome = txtPesquisa.getText();

        List<Fornecedor> fornecedores;

        try{
            if(nome.isEmpty()){
                fornecedores = FornecedorRepository.listar();
            } else {
                fornecedores = FornecedorRepository.listarPorNome(nome);
            }

            model.setRowCount(0);

            for(Fornecedor f : fornecedores){
                model.addRow(f.toRow());
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao listar: " + ex.getMessage()
            );
        }
    }
}