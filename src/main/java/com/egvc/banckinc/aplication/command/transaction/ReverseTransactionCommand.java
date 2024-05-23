package com.egvc.banckinc.aplication.command.transaction;


public record ReverseTransactionCommand(
        String cardId,
        String transactionId
) {}
