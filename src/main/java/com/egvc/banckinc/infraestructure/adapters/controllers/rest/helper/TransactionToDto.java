package com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper;

import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionToDto {

    public TransactionResponse toDto(Transaction transaction) {

        return new TransactionResponse(
                transaction.getCardNumber(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCurrency().name()
        );
    }

    public TransactionPurchaseResponse toPurchaseDto(Transaction transaction) {
        return new TransactionPurchaseResponse(transaction.getCardNumber(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCurrency().name(),
                transaction.getStatus().name());
    }
}
