package com.egvc.banckinc.infraestructure.adapters.persistence.repository.product;

import com.egvc.banckinc.domain.model.ProductCard;
import com.egvc.banckinc.domain.repository.ProductRepository;
import com.egvc.banckinc.infraestructure.adapters.persistence.helper.ProductCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductAdapterRepository implements ProductRepository {

    private final ProductJpaCardRepository productJpaCardRepository;
    private final ProductCardMapper productCardMapper;
    @Override
    public Optional<ProductCard> findById(Long id) {
        return productJpaCardRepository.findById(id)
                .map(productCardMapper::toData);
    }
}
