package model;

public class Login {
    
    private String usuario;
    private Integer senha;

    public Login(String usuario, Integer senha) {
        this.usuario = usuario;
        this.senha = senha;
    }    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }
    
}