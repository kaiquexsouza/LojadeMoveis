package entidades;

public class Fornecedor implements RowModel {
    private int id_fornecedor;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;

    public Fornecedor() {

    }

    public Fornecedor(int id_fornecedor, String nome, String cnpj, String telefone, String email, String endereco) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{
                nome, cnpj, telefone, email, endereco
        };
    }
}
