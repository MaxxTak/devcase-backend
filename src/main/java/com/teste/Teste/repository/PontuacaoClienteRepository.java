package com.teste.Teste.repository;

import com.teste.Teste.model.PontuacaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PontuacaoClienteRepository extends JpaRepository<PontuacaoCliente,Long> {
    @Query(value = "SELECT SUM(pontos) FROM pontuacao_cliente where cliente_id = :id ", nativeQuery = true)
    Float findPontos(@Param("id") Long id);
}
