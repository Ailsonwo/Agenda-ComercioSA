package br.com.comerciosa.agenda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TipoContato tipo;

    @Column(length = 100, nullable = false )
    @NotBlank(message = "Valor do contato é obrigatório.")
    private String valor;

    @Column
    private String observacao;




    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoContato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContato tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }




}

