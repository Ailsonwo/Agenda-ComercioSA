package br.com.comerciosa.agenda.exception;

public class CpfDuplicadoException extends RuntimeException {
    public CpfDuplicadoException(String cpf) {
        super("CPF já cadastrado ("+cpf+")");
    }
}
