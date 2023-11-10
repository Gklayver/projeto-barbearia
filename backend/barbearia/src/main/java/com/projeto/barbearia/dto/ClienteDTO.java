package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Cliente;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Character genero;

    public ClienteDTO(){

    }

    public ClienteDTO(Long id, String nome, String email, String telefone, Character genero) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
    }
    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        genero = entity.getGenero();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }
}
