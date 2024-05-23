package com.egvc.banckinc.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsufficientFundsExceptionTest {

    @Test
    void testInsufficientFundsExceptionMessage() {
        InsufficientFundsException exception = new InsufficientFundsException();
        assertEquals("Fondos insuficientes", exception.getMessage());
    }

}