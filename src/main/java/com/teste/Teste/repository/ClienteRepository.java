package com.teste.Teste.repository;

import com.teste.Teste.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
