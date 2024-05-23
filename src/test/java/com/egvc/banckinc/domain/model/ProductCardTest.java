package com.egvc.banckinc.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCardTest {

    @Test
    void testProductCardConstructor() {
        Long idProduct = 123L;
        String productName = "Test Product";
        ProductCard productCard = new ProductCard(idProduct, productName);

        assertEquals(idProduct, productCard.getIdProduct());
        assertEquals(productName, productCard.getProductName());
    }

    @Test
    void testProductCardDefaultConstructor() {
        ProductCard productCard = new ProductCard();

        assertNull(productCard.getIdProduct());
        assertNull(productCard.getProductName());
    }

}