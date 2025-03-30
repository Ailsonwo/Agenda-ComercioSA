package br.com.comerciosa.agenda.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {


    @Id
    @Column(length = 14)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "America/Sao_Paulo")
    @Column(name = "data_nascimento", nullable = false )
    @Past
    private LocalDate dataNascimento;

    @Column(length = 255)
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setData_nascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}


