package com.egvc.banckinc.domain.exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException() {
        super("Transaccion no encontrada");
    }
}
