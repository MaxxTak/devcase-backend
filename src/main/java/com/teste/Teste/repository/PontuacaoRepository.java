package com.teste.Teste.repository;

import com.teste.Teste.model.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PontuacaoRepository extends JpaRepository<Pontuacao,Long> {
    @Query(value = "SELECT p.valor FROM pontuacoes p WHERE p.min < :valor AND p.max > :valor", nativeQuery = true)
    Integer findPontuacao(@Param("valor") Double valor);

}
