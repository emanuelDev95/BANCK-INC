package com.egvc.banckinc.domain.exceptions;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException() {
        super("Tarjeta no encontrada");
    }
}
