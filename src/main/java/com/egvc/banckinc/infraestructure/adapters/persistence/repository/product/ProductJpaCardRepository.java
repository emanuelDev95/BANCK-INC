package com.egvc.banckinc.infraestructure.adapters.persistence.repository.product;

import com.egvc.banckinc.infraestructure.adapters.persistence.entities.ProductCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaCardRepository extends JpaRepository<ProductCardEntity, Long> {
}
