package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Administrador;
import com.projeto.barbearia.entities.Agendamento;

import java.io.Serializable;
import java.util.List;

public class AdministradorDTO implements Serializable {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;

    public AdministradorDTO(List<Agendamento> obj){

    }

    public AdministradorDTO(Long id, String nome, String email, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public AdministradorDTO(Administrador entity){
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        login = entity.getLogin();
        senha = entity.getSenha();
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
