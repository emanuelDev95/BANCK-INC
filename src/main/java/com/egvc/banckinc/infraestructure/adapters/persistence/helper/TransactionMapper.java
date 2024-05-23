package com.egvc.banckinc.infraestructure.adapters.persistence.helper;

import com.egvc.banckinc.domain.model.Currency;
import com.egvc.banckinc.domain.model.StatusTransaction;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.infraestructure.adapters.helper.MapperGeneric;
import com.egvc.banckinc.infraestructure.adapters.persistence.entities.CardEntity;
import com.egvc.banckinc.infraestructure.adapters.persistence.entities.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements MapperGeneric<Transaction, TransactionEntity> {
    @Override
    public TransactionEntity toEntity(Transaction data) {

        var transaction =  TransactionEntity.builder()
                .idTransaction(data.getTransactionId())
                .card(CardEntity.builder()
                        .idCard(data.getCardId())
                        .build())
                .transactionAmount(data.getAmount())
                .transactionDate(data.getDate())
                .transactionDescription(data.getDescription())
                .transactionStatus(data.getStatus().name())
                .build();

        if(data.getTransactionId()!=null){
            transaction.setIdTransaction(data.getTransactionId());
        }
        return transaction;
    }

    @Override
    public Transaction toData(TransactionEntity entity) {
        return new Transaction(
                entity.getIdTransaction(),
                entity.getTransactionDate(),
                entity.getTransactionAmount(),
                entity.getTransactionDescription(),
                Currency.USD,
                entity.getCard().getCardNumber(),
                StatusTransaction.valueOf(entity.getTransactionStatus())
        );
    }
}
