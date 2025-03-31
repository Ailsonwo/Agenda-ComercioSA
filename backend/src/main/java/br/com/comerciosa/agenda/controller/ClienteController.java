package br.com.comerciosa.agenda.controller;

import br.com.comerciosa.agenda.dto.response.ApiResponseDTO;
import br.com.comerciosa.agenda.exception.RegistroNaoEncontradoException;
import br.com.comerciosa.agenda.model.Cliente;
import br.com.comerciosa.agenda.exception.CpfDuplicadoException;
import br.com.comerciosa.agenda.model.Contato;
import br.com.comerciosa.agenda.repository.ClienteRepository;
import br.com.comerciosa.agenda.repository.ContatoRepository;
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
    @Autowired
    private ContatoRepository contatoRepository;

    @PostMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<Cliente>> cadastrar(@RequestBody Cliente cliente){
        Optional<Cliente> optCliente = clienteRepository.findByCpf(cliente.getCpf());
        if(optCliente.isPresent()){
            throw new CpfDuplicadoException(cliente.getCpf());
        }
        Cliente cl = clienteRepository.save(cliente);
        return ResponseEntity.ok(ApiResponseDTO.success(cl));
    }

    @PutMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<Cliente>> atualizar(@RequestBody Cliente cliente){
        Optional<Cliente> optCliente = clienteRepository.findByCpf(cliente.getCpf());

        if(optCliente.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        Cliente cl = clienteRepository.save(cliente);
        return ResponseEntity.ok(ApiResponseDTO.success(cl));
    }

    @DeleteMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<String>> delete(@RequestParam String cpf){
        Optional<Cliente> cl = clienteRepository.findByCpf(cpf);

        if(cl.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        List<Contato> listContato = contatoRepository.findByClienteCpf(cpf);
        listContato.forEach(contato -> {
            contatoRepository.delete(contato);
        });

        clienteRepository.delete(cl.get());
        return ResponseEntity.ok(ApiResponseDTO.success("Cliente de CPF " + cpf +" deletado com sucesso"));
    }

    @GetMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<List<Cliente>>> getClientes(){
        List<Cliente> clienteList = clienteRepository.findAll();

        return ResponseEntity.ok(ApiResponseDTO.success(clienteList));
    }

    @GetMapping(value = "/buscaNome/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<List<Cliente>>> getClientes(@RequestParam(value = "nome")  String nome){
        List<Cliente> clienteList = clienteRepository.findByNomeContaining(nome);
        
        return ResponseEntity.ok(ApiResponseDTO.success(clienteList));
    }

    @GetMapping(value = "/buscaCpf/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<Cliente>> getClienteByCpf(@RequestParam(value = "cpf")  String cpf){
        Optional<Cliente> cl = clienteRepository.findByCpf(cpf);

        if(cl.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        return ResponseEntity.ok(ApiResponseDTO.success(cl.get()));
    }
}
