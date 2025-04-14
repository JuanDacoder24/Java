package com.dawbank;

public class AvisarHaciendaException extends Exception {
    public AvisarHaciendaException(String message) {
        super("Error en la operacion, avisaremos a hacienda");
    }

}
