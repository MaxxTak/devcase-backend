package com.teste.Teste.repository;

import com.teste.Teste.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findAllByOrderByDataDesc();

    @Query(value = "SELECT * FROM vendas where vendas.data >= \":dataI\" and vendas.data <= \":dataF\"", nativeQuery = true)
    List<Venda> findPeriodo(@Param("dataI") String dataI, @Param("dataF") String dataF);

    @Query(value = "SELECT * FROM vendas inner join clientes on cliente_id = clientes.id where sexo = :genero", nativeQuery = true)
    List<Venda> findGenero(@Param("genero") String genero);
}
