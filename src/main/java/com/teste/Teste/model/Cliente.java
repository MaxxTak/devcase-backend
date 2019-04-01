package com.teste.Teste.model;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String email;
    @Column
    private char sexo;

    @OneToOne()
    @JoinColumn(name="id")
    private Endereco endereco_id;

    public Cliente(){

    }

    public Cliente(String nome, String cpf, String email, Character sexo, Endereco endereco_id){
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setSexo(sexo);
        this.setEndereco_id(endereco_id);
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(Endereco endereco_id) {
        this.endereco_id = endereco_id;
    }
}
