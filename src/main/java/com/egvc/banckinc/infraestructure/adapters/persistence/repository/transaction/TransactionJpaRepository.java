package com.egvc.banckinc.infraestructure.adapters.persistence.repository.transaction;

import com.egvc.banckinc.infraestructure.adapters.persistence.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    Optional<TransactionEntity> findByCardCardNumberAndIdTransaction(String cardId, Long idTransaction);
}
