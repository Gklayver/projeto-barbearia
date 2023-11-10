package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Funcionario;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private Character cargo;
    private Character genero;
    private String login;
    private String senha;

    public FuncionarioDTO(){

    }

    public FuncionarioDTO(Funcionario entity){
        id  = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        cargo = entity.getCargo();
        genero = entity.getGenero();
        login = entity.getLogin();
        senha = entity.getSenha();

    }

    public FuncionarioDTO(Long id, String nome, String email, Character cargo, Character genero, String login, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.genero = genero;
        this.login = login;
        this.senha = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getCargo() {
        return cargo;
    }

    public void setCargo(Character cargo) {
        this.cargo = cargo;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

