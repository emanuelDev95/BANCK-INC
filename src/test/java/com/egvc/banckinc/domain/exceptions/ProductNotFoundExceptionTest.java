package com.egvc.banckinc.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNotFoundExceptionTest {

    @Test
    void testProductNotFoundExceptionMessage() {
        ProductNotFoundException exception = new ProductNotFoundException();
        assertEquals("Producto no encontrado", exception.getMessage());
    }

}