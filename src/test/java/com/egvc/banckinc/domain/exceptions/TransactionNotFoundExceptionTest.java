package com.egvc.banckinc.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionNotFoundExceptionTest {

    @Test
    void testTransactionNotFoundExceptionMessage() {
        TransactionNotFoundException exception = new TransactionNotFoundException();
        assertEquals("Transaccion no encontrada", exception.getMessage());
    }

}