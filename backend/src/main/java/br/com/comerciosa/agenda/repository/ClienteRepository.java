package br.com.comerciosa.agenda.repository;

import br.com.comerciosa.agenda.model.Cliente;
import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {


    List<Cliente> findByNomeContaining(String nome);

    @Query("SELECT c FROM Cliente c WHERE REPLACE(REPLACE(c.cpf, '.', ''), '-', '') = REPLACE(REPLACE(:cpf, '.', ''), '-', '')")
    Optional<Cliente> findByCpf(@Param("cpf") String cpf);

}
