package com.mensagens.usuario;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String profissao;

    public Usuario() {}

    public Usuario(String email, String senha, String profissao) {
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
    }

    public Usuario(int id, String email, String senha, String profissao) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
}
