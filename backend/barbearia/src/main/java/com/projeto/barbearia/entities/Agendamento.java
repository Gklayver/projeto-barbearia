package com.projeto.barbearia.entities;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;

    //Relação agendamento possui serviços
    /*
    @ManyToMany
    @JoinTable(name = "tb_agendamento_servicos",
            joinColumns = @JoinColumn (name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn (name = "servicos_id"))
    private List<Servico> servicos = new ArrayList<>();
*/
    //Relação funcionario registra agendamento
    /*
    @ManyToMany(mappedBy = "agendamento")
    private List<Funcionario> funcionario = new ArrayList<>();
*/
    //Relação funcionario registra agendamento
/*
    @OneToOne
    @MapsId
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @MapsId
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
   */

    @ManyToMany
    @JoinTable(
            name = "agendamento_servico",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos = new ArrayList<>();

    private Long idCliente;
    private Long idFuncionario;
    // private String servicos;
    public Agendamento() {
    }

    public Agendamento(Long id, LocalDate data, LocalTime hora, List<Servico> servicos, Long idCliente, Long idFuncionario) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.servicos = servicos;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
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

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
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
