package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Servico;

import java.io.Serializable;

public class ServicoDTO implements Serializable {
    private Long id;
    private String nome;
    private Double valor;


    public ServicoDTO() {
    }

    public ServicoDTO(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    public ServicoDTO(Servico entity) {
        id = entity.getId();
        nome = entity.getNome();
        valor = entity.getValor();
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
