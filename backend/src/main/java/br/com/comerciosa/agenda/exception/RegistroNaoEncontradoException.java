package br.com.comerciosa.agenda.exception;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException() {
        super("Não há registros.");
    }
}
