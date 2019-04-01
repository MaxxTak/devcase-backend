package com.teste.Teste.controller;

import com.teste.Teste.model.Pontuacao;
import com.teste.Teste.model.PontuacaoCliente;
import com.teste.Teste.repository.PontuacaoClienteRepository;
import com.teste.Teste.repository.PontuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pontuacao")
public class PontuacaoController {

    @Autowired
    private PontuacaoRepository repository;
    private PontuacaoClienteRepository pontuacaoClienteRepository;

    public PontuacaoController(PontuacaoRepository pontuacaoRepository, PontuacaoClienteRepository pontuacaoClienteRepository){
        this.repository = pontuacaoRepository;
        this.pontuacaoClienteRepository = pontuacaoClienteRepository;
    }


    @GetMapping
    @ResponseBody
    public List<Pontuacao> findAll(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseBody
    public Pontuacao store(Double min, Double max, Integer valor) {
        Pontuacao pontuacao = new Pontuacao( min, max, valor);
        repository.save(pontuacao);
        return pontuacao;
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public Pontuacao update(@PathVariable("id") Long id, Double min, Double max, Integer valor){
        Optional<Pontuacao> pontuacao_aux = repository.findById(id);
        Pontuacao pontuacao = pontuacao_aux.get();
        if(!(min==null))
            pontuacao.setMin(min);
        if(!(max==null))
            pontuacao.setMax(max);
        if(!(valor==null))
            pontuacao.setValor(valor);

        repository.save(pontuacao);
        return pontuacao;
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public boolean destroy(@PathVariable("id") Long id){
        Optional<Pontuacao> pontuacao_aux = repository.findById(id);

        //return venda_aux;

        if(!(pontuacao_aux.isEmpty())){
            Pontuacao pontuacao = pontuacao_aux.get();
            repository.delete(pontuacao);
            return true;
        }else
            return false;

    }

    @RequestMapping("/{id}")
    public Optional<Pontuacao> show(@PathVariable("id") int id){
        return repository.findById((long) id);
    }

    @RequestMapping("/pontos/cliente/{id}")
    @ResponseBody
    public Float show(@PathVariable("id") Long id){
        Float pontos = pontuacaoClienteRepository.findPontos(id);
        return pontos==null ? 0 : pontos;
    }
}
