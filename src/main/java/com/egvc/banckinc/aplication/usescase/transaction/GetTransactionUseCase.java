package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.query.transaction.TransactionQuery;
import com.egvc.banckinc.domain.exceptions.TransactionNotFoundException;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public Transaction getTransaction(TransactionQuery query) {

        return transactionRepository.getTransationById(query.TransactionId())
                .orElseThrow(TransactionNotFoundException::new);

    }
}
