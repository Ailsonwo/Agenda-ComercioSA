package br.com.comerciosa.agenda.controller;

import br.com.comerciosa.agenda.exception.RegistroNaoEncontradoException;
import br.com.comerciosa.agenda.model.Cliente;
import br.com.comerciosa.agenda.exception.CpfDuplicadoException;
import br.com.comerciosa.agenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(value = "/", produces = "application/json")
    private ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
        try {
            Cliente cl = clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(cl, HttpStatus.CREATED);
        }catch(DataIntegrityViolationException e) {
            throw new CpfDuplicadoException(cliente.getCpf());
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    private ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente){
        Cliente cl = clienteRepository.save(cliente);
        return new ResponseEntity<Cliente>(cl, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/", produces = "application/json")
    private ResponseEntity<String> delete(@RequestParam String cpf){
        Optional<Cliente> cl = clienteRepository.findByCpf(cpf);
        if(cl.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        clienteRepository.delete(cl.get());
        return new ResponseEntity<String>("Cliente de CPF " + cpf +" deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    private ResponseEntity<List<Cliente>> getClientes(){
        List<Cliente> clienteList = clienteRepository.findAll();

        if(clienteList.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return new ResponseEntity<List<Cliente>>(clienteList, HttpStatus.OK);
    }

    @GetMapping(value = "/buscaNome/", produces = "application/json")
    private ResponseEntity<List<Cliente>> getClientes(@RequestParam(value = "nome")  String nome){
        List<Cliente> clienteList = clienteRepository.findByNomeContaining(nome);

        if(clienteList.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return new ResponseEntity<List<Cliente>>(clienteList, HttpStatus.OK);
    }

    @GetMapping(value = "/buscaCpf/", produces = "application/json")
    private ResponseEntity<Cliente> getClienteByCpf(@RequestParam(value = "cpf")  String cpf){
        Optional<Cliente> cl = clienteRepository.findByCpf(cpf);

        if(cl.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        return new ResponseEntity<Cliente>(cl.get(), HttpStatus.OK);
    }
}
