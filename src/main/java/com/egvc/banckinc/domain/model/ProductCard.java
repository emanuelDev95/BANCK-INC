package com.egvc.banckinc.domain.model;

import lombok.Getter;

@Getter
public class ProductCard {

    private Long idProduct;
    private String productName;

    public ProductCard() {
    }
    public ProductCard(Long idProduct, String productName) {
        this.idProduct = idProduct;
        this.productName = productName;
    }


}
