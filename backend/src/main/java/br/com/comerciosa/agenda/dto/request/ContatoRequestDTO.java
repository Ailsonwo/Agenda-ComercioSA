package br.com.comerciosa.agenda.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public class ContatoRequestDTO {


    private Integer id;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "Valor é obrigatório")
    private String valor;

    @JsonProperty("cliente_cpf")
    @CPF(message = "CPF do cliente é obrigatório")
    private String clienteCpf; // Campo que receberá "cliente-cpf"

    private String observacao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
