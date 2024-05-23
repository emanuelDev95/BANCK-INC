package com.egvc.banckinc.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTransactionTest {


    @Test
    void testEnumValues() {
        assertEquals("ACTIVE", StatusTransaction.ACTIVE.name());
        assertEquals("INACTIVE", StatusTransaction.INACTIVE.name());
        assertEquals("ANULED", StatusTransaction.ANULED.name());
        assertEquals("CANCELED", StatusTransaction.CANCELED.name());
        assertEquals("REALIZED", StatusTransaction.REALIZED.name());
    }

}