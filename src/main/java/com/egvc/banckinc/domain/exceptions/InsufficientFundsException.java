package com.egvc.banckinc.domain.exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException() {
        super("Fondos insuficientes");
    }
}
