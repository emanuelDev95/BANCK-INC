package com.egvc.banckinc.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardBlockedExceptionTest {

    @Test
    void testCardBlockedExceptionMessage() {
        CardBlockedException exception = new CardBlockedException();
        assertEquals("Tarjeta bloquada o vencida", exception.getMessage());
    }

}