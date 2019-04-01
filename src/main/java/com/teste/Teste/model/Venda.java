package com.teste.Teste.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    //@Column
   // private Long cliente_id;
    @ManyToOne()
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente_id;

    @Column
    private Long pdv_id;
    @Column
    private double valor;
    @Column
    private Date data;
    @Column
    private Long vendedor_id;

    public Venda(){

    }
    public Venda(Cliente cliente_id, Long pdv_id, double valor, Date data, Long vendedor_id){
        this.setCliente_id(cliente_id);
        this.setPdv_id(pdv_id);
        this.setValor(valor);
        this.setData(data);
        this.setVendedor_id(vendedor_id);
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getPdv_id() {
        return pdv_id;
    }

    public void setPdv_id(Long pdv_id) {
        this.pdv_id = pdv_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(Long vendedor_id) {
        this.vendedor_id = vendedor_id;
    }
}
