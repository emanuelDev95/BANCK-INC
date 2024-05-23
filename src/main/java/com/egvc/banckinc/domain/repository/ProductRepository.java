package com.egvc.banckinc.domain.repository;

import com.egvc.banckinc.domain.model.ProductCard;

import java.util.Optional;

public interface ProductRepository {

    Optional<ProductCard> findById(Long id);
}
