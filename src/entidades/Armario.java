package entidades;

import entidades.enumeracao.TipoMovel;

public class Armario extends Movel {
    private int numeroPortas;

    public Armario () { }

    public Armario(int id, String cor, String descricao, String material, float altura, float largura, float comprimento, float preco, TipoMovel tipoMovel, int numeroPortas) {
        super(id, cor, descricao, material, altura, largura, comprimento, preco, tipoMovel);
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }
}
