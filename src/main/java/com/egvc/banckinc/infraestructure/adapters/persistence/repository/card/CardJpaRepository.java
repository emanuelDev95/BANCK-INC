package com.egvc.banckinc.infraestructure.adapters.persistence.repository.card;

import com.egvc.banckinc.infraestructure.adapters.persistence.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardJpaRepository extends JpaRepository<CardEntity,Long> {

    Optional<CardEntity> findByCardNumber(String cardNumber);
}
