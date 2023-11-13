package com.projeto.barbearia.dto;

import com.projeto.barbearia.entities.Agendamento;
import com.projeto.barbearia.entities.Cliente;
import com.projeto.barbearia.entities.Funcionario;
import com.projeto.barbearia.entities.Servico;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDTO {
    private Long id;
    private LocalDate data;
    private LocalTime hora;

    private Long idCliente;
    private Long idFuncionario;


    private List<Servico> servicos;
    public AgendamentoDTO(){}

    public AgendamentoDTO(Long id, LocalDate data, LocalTime hora, Long idCliente, Long idFuncionario, List<Servico> servicos) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.servicos = servicos;
    }

    public AgendamentoDTO(Agendamento entity){
        id = entity.getId();
        data = entity.getData();
        hora = entity.getHora();
        idFuncionario = entity.getIdFuncionario();
        idCliente = entity.getIdCliente();
        servicos = entity.getServicos();
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

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}