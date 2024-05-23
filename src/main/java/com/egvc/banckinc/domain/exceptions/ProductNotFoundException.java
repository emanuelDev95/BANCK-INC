package com.egvc.banckinc.domain.exceptions;


public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException() {
        super("Producto no encontrado");
    }

}
