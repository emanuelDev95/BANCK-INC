package com.egvc.banckinc.domain.repository;

import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.model.Transaction;

import java.util.Optional;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    Optional<Transaction> getTransationById(Long id);
    Optional<Transaction> getTransationByCard(String card, Long id);

}

