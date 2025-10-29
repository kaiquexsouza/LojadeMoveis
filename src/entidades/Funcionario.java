package entidades;

public class Funcionario {
    private int id_funcionario;
    private String usuario;
    private String senha;

    public Funcionario() {

    }

    public Funcionario(int id_funcionario, String usuario, String senha) {
        this.id_funcionario = id_funcionario;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
