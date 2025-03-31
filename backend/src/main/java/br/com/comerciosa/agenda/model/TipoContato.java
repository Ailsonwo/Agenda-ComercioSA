package br.com.comerciosa.agenda.model;

public enum TipoContato {
    TELEFONE, EMAIL;

    public static TipoContato fromString(String value) {
        return value == null ? null : TipoContato.valueOf(value.toUpperCase());
    }
}