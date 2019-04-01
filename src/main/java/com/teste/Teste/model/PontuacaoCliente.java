package com.teste.Teste.model;

import javax.persistence.*;

@Entity
@Table(name = "pontuacao_cliente")
public class PontuacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne()
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente_id;

    @OneToOne()
    @JoinColumn(name="id")
    private Venda venda_id;

    @Column
    private int pontos;

    public Long getId() {
        return id;
    }

    public PontuacaoCliente(){}

    public PontuacaoCliente(Cliente cliente_id, Venda venda_id, Integer pontos){
        this.setCliente_id(cliente_id);
        this.setVenda_id(venda_id);
        this.setPontos(pontos);
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Venda getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Venda venda_id) {
        this.venda_id = venda_id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
