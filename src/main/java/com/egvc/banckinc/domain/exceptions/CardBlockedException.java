package com.egvc.banckinc.domain.exceptions;

public class CardBlockedException extends RuntimeException{

    public CardBlockedException() {
        super("Tarjeta bloquada o vencida");
    }
}
