package br.com.comerciosa.agenda.exception;

public class SemIdException extends RuntimeException {
    public SemIdException() {
        super("Obrigatório ID para atualizar");
    }
}
