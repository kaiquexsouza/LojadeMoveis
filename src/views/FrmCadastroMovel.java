package views;

import entidades.Fornecedor;
import entidades.Movel;
import entidades.enumeracao.TipoMovel;
import repositorio.FornecedorRepository;
import repositorio.MovelRepository;
import util.DataUtil;
import util.FormUtil;
import util.Validador;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.Normalizer;

public class FrmCadastroMovel extends JFrame {
    private JTextField txtDescricao;
    private JTextField txtCor;
    private JTextField txtMaterial;
    private JFormattedTextField ftxtAltura;
    private JFormattedTextField ftxtLargura;
    private JFormattedTextField ftxtComprimento;
    private JFormattedTextField ftxtPreco;
    private JFormattedTextField ftxtCnpjFornecedor;
    private JComboBox tipoMovel;
    private JPanel jpanel;
    private JButton btnSalvar;
    private JButton btnCancelar;

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
        for (TipoMovel t : TipoMovel.values()) {
            tipoMovel.addItem(t.name());
        }
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] campos = {txtDescricao, txtCor, txtMaterial, ftxtAltura, ftxtLargura, ftxtComprimento, ftxtPreco, ftxtCnpjFornecedor};

                if (FormUtil.hasEmpty(campos)) {
                    JOptionPane.showMessageDialog(FrmCadastroMovel.this,
                            "Preencha todos os campos",
                            "Erro ao salvar",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tipoMovel.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(FrmCadastroMovel.this,
                            "Selecione o tipo de móvel",
                            "Erro ao selecionar móvel",
                            JOptionPane.ERROR_MESSAGE
                    );
                    tipoMovel.requestFocus();
                    return;
                }
                String cnpjLimpo = FormUtil.limparMascara(ftxtCnpjFornecedor.getText());

                if (!Validador.validarCNPJ(cnpjLimpo)) {
                    JOptionPane.showMessageDialog(null,
                            "CNPJ inválido! Deve conter 14 dígitos");
                    return;
                }

                Fornecedor fornecedor = null;
                try {
                    fornecedor = FornecedorRepository.buscarPorCnpj(cnpjLimpo);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (fornecedor == null) {
                    JOptionPane.showMessageDialog(null,
                            "Fornecedor não encontrado!");
                    return;
                }

                Movel movel = new Movel();
                movel.setDescricao(txtDescricao.getText());
                movel.setCor(txtCor.getText());
                movel.setMaterial(txtMaterial.getText());
                movel.setAltura(DataUtil.parseInt(ftxtAltura.getText()));
                movel.setLargura(DataUtil.parseInt(ftxtLargura.getText()));
                movel.setComprimento(DataUtil.parseInt(ftxtComprimento.getText()));
                movel.setPreco(DataUtil.parseInt(ftxtPreco.getText()));
                movel.setTipoMovel(TipoMovel.valueOf(tipoMovel.getSelectedItem().toString()));
                movel.setFornecedor(fornecedor);

                try {
                    MovelRepository.inserir(movel);
                    JOptionPane.showMessageDialog(
                            null,
                            "Salvo com Sucesso!");
                    FormUtil.cleanJTexts(campos);
                    tipoMovel.setSelectedIndex(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Erro ao cadastrar movel: " + ex.getMessage()
                    );
                }
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

    public void createUIComponents() {
        try {
            MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
            maskCnpj.setPlaceholderCharacter('_');
            maskCnpj.setValueContainsLiteralCharacters(false);
            ftxtCnpjFornecedor = new JFormattedTextField(maskCnpj);
            ftxtCnpjFornecedor.setFocusLostBehavior(JFormattedTextField.PERSIST);

            ftxtAltura = new JFormattedTextField();
            ftxtLargura = new JFormattedTextField();
            ftxtComprimento = new  JFormattedTextField();
            ftxtPreco = new  JFormattedTextField();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}