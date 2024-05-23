package com.egvc.banckinc.infraestructure.adapters.persistence.helper;

import com.egvc.banckinc.domain.model.ProductCard;
import com.egvc.banckinc.infraestructure.adapters.persistence.entities.ProductCardEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductCardMapper {

    public ProductCardEntity toEntity(ProductCard productCard) {
        return ProductCardEntity.builder()
                .idProduct(productCard.getIdProduct())
                .productName(productCard.getProductName())
                .build();
    }


    public ProductCard toData(ProductCardEntity productCardEntity) {
        return new ProductCard(productCardEntity.getIdProduct(), productCardEntity.getProductName());
    }
}
