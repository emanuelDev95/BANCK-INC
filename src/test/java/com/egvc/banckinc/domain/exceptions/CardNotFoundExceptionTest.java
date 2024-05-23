package com.egvc.banckinc.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardNotFoundExceptionTest {

    @Test
    void testCardNotFoundExceptionMessage() {
        CardNotFoundException exception = new CardNotFoundException();
        assertEquals("Tarjeta no encontrada", exception.getMessage());
    }

}