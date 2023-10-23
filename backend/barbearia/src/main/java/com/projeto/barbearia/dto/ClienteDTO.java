package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Cliente;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Character sexo;

    public ClienteDTO(){

    }

    public ClienteDTO(Long id, String nome, String email, String telefone, Character sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
    }
    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        sexo = entity.getSexo();
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
}
