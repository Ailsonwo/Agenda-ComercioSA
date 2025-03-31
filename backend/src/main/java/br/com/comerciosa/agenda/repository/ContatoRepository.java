package br.com.comerciosa.agenda.repository;

import br.com.comerciosa.agenda.model.Cliente;
import br.com.comerciosa.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    List<Contato> findByClienteCpf(String cliente_cpf);
}
