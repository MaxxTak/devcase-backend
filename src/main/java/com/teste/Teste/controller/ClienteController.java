package com.teste.Teste.controller;

import com.teste.Teste.model.Cliente;
import com.teste.Teste.model.Endereco;
import com.teste.Teste.repository.ClienteRepository;
import com.teste.Teste.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    private EnderecoRepository enderecoRepository;

    public ClienteController(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository){
        this.repository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Cliente> findAll(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseBody
    public Cliente store(String nome, String cpf, String email, Character sexo,String endereco, String cep, String numero, String complemento, String bairro, String cidade, String estado) {
        Endereco end = new Endereco(endereco, cep, numero, complemento, bairro, cidade, estado);
        enderecoRepository.save(end);
       //Integer endereco_id = Math.toIntExact(endereco.getId());
        Cliente cliente = new Cliente(nome,cpf,email,sexo,end);
        repository.save(cliente);
        return cliente;
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public Cliente update(@PathVariable("id") Long id, String nome, String cpf, String email, Character sexo){
        Optional<Cliente> venda_aux = repository.findById(id);
        Cliente cliente = venda_aux.get();
        if(!(nome==null))
            cliente.setNome(nome);
        if(!(cpf==null))
            cliente.setCpf(cpf);
        if(!(email==null))
            cliente.setEmail(email);
        if(!(sexo==null))
            cliente.setSexo(sexo);

        repository.save(cliente);
        return cliente;
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public boolean destroy(@PathVariable("id") Long id){
        Optional<Cliente> cliente_aux = repository.findById(id);

        //return venda_aux;

        if(!(cliente_aux.isEmpty())){
            Cliente cliente = cliente_aux.get();
            repository.delete(cliente);
            return true;
        }else
            return false;

    }

    @RequestMapping("/{id}")
    public Optional<Cliente> show(@PathVariable("id") int id){
        return repository.findById((long) id);
    }
}
