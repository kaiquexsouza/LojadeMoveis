package entidades;

import entidades.enumeracao.TipoMovel;

public class Movel {
    private int id;
    private String cor;
    private String descricao;
    private String material;
    private float altura;
    private float largura;
    private float comprimento;
    private float preco;
    private TipoMovel tipoMovel;
    private Fornecedor fornecedor;

    public Movel() {

    }

    public Movel(int id, String cor, String descricao, String material, float altura, float largura, float comprimento, float preco, TipoMovel tipoMovel, Fornecedor fornecedor) {
        this.id = id;
        this.cor = cor;
        this.descricao = descricao;
        this.material = material;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
        this.tipoMovel = tipoMovel;
        this.fornecedor = fornecedor;
    }

    public int getId() {
        return id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public TipoMovel getTipoMovel() {
        return tipoMovel;
    }

    public void setTipoMovel(TipoMovel tipoMovel) {
        this.tipoMovel = tipoMovel;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
