package br.com.comerciosa.agenda.controller;

import br.com.comerciosa.agenda.dto.request.ContatoRequestDTO;
import br.com.comerciosa.agenda.dto.response.ApiResponseDTO;
import br.com.comerciosa.agenda.exception.RegistroNaoEncontradoException;
import br.com.comerciosa.agenda.exception.SemIdException;
import br.com.comerciosa.agenda.model.Cliente;
import br.com.comerciosa.agenda.model.Contato;
import br.com.comerciosa.agenda.model.TipoContato;
import br.com.comerciosa.agenda.repository.ClienteRepository;
import br.com.comerciosa.agenda.repository.ContatoRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/contato")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<Contato>> cadastrar(@RequestBody ContatoRequestDTO contatoDTO){
        Optional<Cliente> optCliente = clienteRepository.findByCpf(contatoDTO.getClienteCpf());
        if(optCliente.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        Cliente cliente = clienteRepository.findByCpf(contatoDTO.getClienteCpf()).get();
        Contato contato = new Contato();
        contato.setCliente(cliente);
        contato.setTipo(TipoContato.fromString(contatoDTO.getTipo()));
        contato.setValor(contatoDTO.getValor());
        contato.setObservacao(contatoDTO.getObservacao());
        Contato c = contatoRepository.save(contato);
        return ResponseEntity.ok(ApiResponseDTO.success(c));
    }

    @PutMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<Contato>> atualizar(@RequestBody ContatoRequestDTO contatoDTO){
        if (contatoDTO.getId() == null) {
            throw new SemIdException();
        }

        Optional<Cliente> optCliente = clienteRepository.findByCpf(contatoDTO.getClienteCpf());
        Optional<Contato> optContato =  contatoRepository.findById(contatoDTO.getId());

        if(optCliente.isEmpty() | optContato.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        Cliente cliente = optCliente.get();
        Contato contato = new Contato();
        contato.setCliente(cliente);
        contato.setId(contatoDTO.getId());
        contato.setTipo(TipoContato.fromString(contatoDTO.getTipo()));
        contato.setValor(contatoDTO.getValor());
        contato.setObservacao(contatoDTO.getObservacao());

        Contato c = contatoRepository.save(contato);
        return ResponseEntity.ok(ApiResponseDTO.success(c));
    }

    @DeleteMapping(value = "/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<String>> delete(@RequestParam Integer id){
        Optional<Contato> c = contatoRepository.findById(id);
        if(c.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        contatoRepository.delete(c.get());
        return ResponseEntity.ok(ApiResponseDTO.success("Contato deletado com sucesso"));
    }

    @GetMapping(value = "/buscaCpf/", produces = "application/json")
    private ResponseEntity<ApiResponseDTO<List<Contato>>> getContatoByCpf(@RequestParam(value = "cliente_cpf")  String cliente_cpf){
        Optional<Cliente> cl = clienteRepository.findByCpf(cliente_cpf);
        List<Contato> listContato = contatoRepository.findByClienteCpf(cliente_cpf);

        if(cl.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }

        return ResponseEntity.ok(ApiResponseDTO.success(listContato));
    }


}
