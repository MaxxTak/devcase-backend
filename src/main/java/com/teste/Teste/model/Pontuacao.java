package com.teste.Teste.model;

import javax.persistence.*;

@Entity
@Table(name = "pontuacoes")
public class Pontuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private double min;
    @Column
    private double max;
    @Column
    private int valor;

    public Long getId() {
        return id;
    }

    public Pontuacao(){

    }

    public Pontuacao(Double min, Double max, Integer valor){
        this.setMin(min);
        this.setMax(max);
        this.setValor(valor);
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
