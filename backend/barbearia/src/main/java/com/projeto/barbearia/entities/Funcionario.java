package com.projeto.barbearia.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Character cargo;
    private Character genero;
    private String login;
    private String senha;

    //Relação funcionario cadastra cliente
    @ManyToMany
    @JoinTable(name = "tb_funcionario_cliente",
            joinColumns = @JoinColumn (name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn (name = "cliente_id"))
    private List<Cliente> cliente = new ArrayList<>();

    //Relação funcionario cadastra agendamento
    /*@ManyToMany
    @JoinTable(name = "tb_funcionario_agendamento",
            joinColumns = @JoinColumn (name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn (name = "agendamento_id"))
    private List<Agendamento> agendamento = new ArrayList<>();
    */
//    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Agendamento agendamento;

    public Funcionario() {
    }
    public Funcionario(Long id, String nome, String email, Character cargo, Character genero, String login, String password) {
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

    public void setSenha(String password) {
        this.senha = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
