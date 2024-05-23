package com.egvc.banckinc.infraestructure.adapters.persistence.repository.transaction;

import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.domain.repository.TransactionRepository;
import com.egvc.banckinc.infraestructure.adapters.persistence.helper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class TransactionAdapterRepository implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;


    @Override
    public Transaction save(Transaction transaction) {
        var entityPersistence = transactionMapper.toEntity(transaction);
        entityPersistence = transactionJpaRepository.save(entityPersistence);

        return transactionMapper.toData(entityPersistence);
    }

    @Override
    public Optional<Transaction> getTransationById(Long id) {
        return transactionJpaRepository.findById(id)
                .map(transactionMapper::toData);
    }

    @Override
    public Optional<Transaction> getTransationByCard(String card, Long id) {
        return transactionJpaRepository.findByCardCardNumberAndIdTransaction(card, id)
                .map(transactionMapper::toData);
    }


}
