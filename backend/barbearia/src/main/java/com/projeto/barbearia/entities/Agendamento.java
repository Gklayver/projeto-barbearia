package com.projeto.barbearia.entities;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private String barbeiro;

    //Relação agendamento possui serviços
    @ManyToMany
    @JoinTable(name = "tb_agendamento_servicos",
            joinColumns = @JoinColumn (name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn (name = "servicos_id"))
    private List<Servico> servicos = new ArrayList<>();

    //Relação funcionario registra agendamento
    @ManyToMany(mappedBy = "agendamento")
    private List<Funcionario> funcionario = new ArrayList<>();
    public Agendamento() {
    }

    public Agendamento(Long id, LocalDate data, LocalTime hora, String barbeiro) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.barbeiro = barbeiro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(String barbeiro) {
        this.barbeiro = barbeiro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
