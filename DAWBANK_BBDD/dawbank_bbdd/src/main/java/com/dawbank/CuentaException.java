package com.dawbank;

public class CuentaException extends Exception {
    public CuentaException(String message) {
        super("Error en la operacion");
    }
}
