package com.projeto.barbearia.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Double valor;

    @ManyToMany
    @JoinTable(name = "tb_servicos_pagamentos",
    joinColumns = @JoinColumn (name = "servico_id"),
    inverseJoinColumns = @JoinColumn (name = "pagamentos_id"))
    private List<Pagamento> pagamentos = new ArrayList<>();
/*
    @ManyToMany(mappedBy = "servicos")
    private List<Agendamento> agendamento = new ArrayList<>();
*/

@ManyToMany(mappedBy = "servicos")
private List<Agendamento> agendamentos;

    public Servico() {
    }
    public Servico(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
