package com.projeto.barbearia.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Character genero;

    //Relação cliente possui agendamento
    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Agendamento> agendamento = new ArrayList<>();

    //Relação funcionario cadastra cliente
    @ManyToMany(mappedBy = "cliente")
    private List<Funcionario> funcionario = new ArrayList<>();

    public Cliente() {

    }
    public Cliente(Long id, String nome, String email, String telefone, Character genero) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
