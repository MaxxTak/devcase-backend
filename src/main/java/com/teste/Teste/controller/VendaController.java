package com.teste.Teste.controller;

import com.teste.Teste.model.Cliente;
import com.teste.Teste.model.Pontuacao;
import com.teste.Teste.model.PontuacaoCliente;
import com.teste.Teste.model.Venda;
import com.teste.Teste.repository.ClienteRepository;
import com.teste.Teste.repository.PontuacaoClienteRepository;
import com.teste.Teste.repository.PontuacaoRepository;
import com.teste.Teste.repository.VendaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository repository;
    private ClienteRepository clienteRepository;
    private PontuacaoRepository pontuacaoRepository;
    private PontuacaoClienteRepository pontuacaoClienteRepository;

    public VendaController(VendaRepository vendaRepository,ClienteRepository clienteRepository,PontuacaoRepository pontuacaoRepository,PontuacaoClienteRepository pontuacaoClienteRepository){
        this.repository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.pontuacaoRepository = pontuacaoRepository;
        this.pontuacaoClienteRepository = pontuacaoClienteRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Venda> findAll(){
        return repository.findAllByOrderByDataDesc();
    }

    @PostMapping
    @ResponseBody
    public Venda store(int cliente_id, int pdv_id, double valor, int vendedor_id) {

        int pontos = pontuacaoRepository.findPontuacao(valor);
        System.out.println("=================================================================================================================");
        System.out.println(pontos);

        LocalDateTime ld = LocalDateTime.now();

        Optional<Cliente> cliente_aux = clienteRepository.findById((long)cliente_id);
        Cliente cliente = cliente_aux.get();
        System.out.println(cliente);
        Date data = java.sql.Timestamp.valueOf(ld);
        Venda venda = new Venda(cliente, (long) pdv_id, valor, data, (long) vendedor_id);
        repository.save(venda);

        PontuacaoCliente pontuacaoCliente = new PontuacaoCliente(cliente, venda, pontos);
        pontuacaoClienteRepository.save(pontuacaoCliente);
        System.out.println(venda);
        System.out.println(pontuacaoCliente);
        System.out.println("=================================================================================================================");
        return venda;
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public Venda update(@PathVariable("id") Long id, Long cliente_id, Long pdv_id, Double valor, Long vendedor_id){
        Optional<Venda> venda_aux = repository.findById(id);
        Venda venda = venda_aux.get();
        if(!(cliente_id==null)){
            Optional<Cliente> cliente_aux = clienteRepository.findById(cliente_id);
            Cliente cliente = cliente_aux.get();
            venda.setCliente_id(cliente);
        }

        if(!(pdv_id==null))
            venda.setPdv_id(pdv_id);
        if(!(valor==null))
            venda.setValor(valor);
        if(!(vendedor_id==null))
            venda.setVendedor_id(vendedor_id);
        repository.save(venda);
        return venda;
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public boolean destroy(@PathVariable("id") Long id){
        Optional<Venda> venda_aux = repository.findById(id);

        //return venda_aux;

        if(!(venda_aux.isEmpty())){
            Venda venda = venda_aux.get();
            repository.delete(venda);
            return true;
        }else
            return false;

    }

    @RequestMapping("/{id}")
    public Optional<Venda> show(@PathVariable("id") int id){
        return repository.findById((long) id);
    }

    @RequestMapping("/periodo/?dataI={dataI}&dataF={dataF}")
    public List<Venda> periodo(@RequestParam("dataI") String dataI, @RequestParam("dataF") String dataF){
        return repository.findPeriodo(dataI,dataF);
    }

    @RequestMapping("/genero/?genero={genero}")
    public List<Venda> genero(@RequestParam("genero") String genero){
        return repository.findGenero(genero);
    }


}
