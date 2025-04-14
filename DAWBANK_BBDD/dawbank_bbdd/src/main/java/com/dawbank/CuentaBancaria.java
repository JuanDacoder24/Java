package com.dawbank;

import java.util.LinkedList;

public class CuentaBancaria {
    private String iban;
    private Cliente titular;
    private double saldo;
    LinkedList <Movimiento> movimientos = new LinkedList<>(); 

    public CuentaBancaria( String iban, Cliente titular){
        this.iban = iban;
        this.titular = titular;
        this.movimientos = new LinkedList<>();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if(saldo >=0.0)
        this.saldo = saldo;
    }

    public void registrarMovimiento(Movimiento r) {
        movimientos.add(r);
    }

    public void ingresarMovimientos(double cantidad) throws CuentaException {
        if (cantidad <= 0) {
            throw new CuentaException("La cantidad debe ser mayor a 0.");
        }
        if (cantidad >= 3000) {
            System.out.println("AVISO: Notificar a hacienda.");
        }

        saldo += cantidad;
        registrarMovimiento(new Movimiento("Ingreso", cantidad));
    }

    public void retirarMovimientos(double cantidad) throws CuentaException {
        if (cantidad <= 0) {
            throw new CuentaException("La cantidad debe ser mayor a 0.");
        }
        if (cantidad >= -50) {
            System.out.println("Retirada no permitida, verificar el saldo.");
        }
        saldo -= cantidad;
        registrarMovimiento(new Movimiento("Retiro", cantidad));
    }

    public void mostrarMovimiento() {
        System.out.println("Movimientos realizados en la cuenta: ");
        for (Movimiento movimiento : movimientos) {
            System.out.println(movimiento.toString());
        }
    }
}
