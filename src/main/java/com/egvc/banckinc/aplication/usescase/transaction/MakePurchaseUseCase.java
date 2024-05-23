package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.command.transaction.MakePurchaseCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.domain.repository.CardRepository;
import com.egvc.banckinc.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakePurchaseUseCase {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public Transaction makePurchase(MakePurchaseCommand command){

        var card = cardRepository.findByCardNumber(command.cardId())
                .orElseThrow(CardNotFoundException::new);

        var transaction = Transaction.createTransaction(card, command.purchaseAmount());
        cardRepository.save(card);
        return transactionRepository.save(transaction);
    }
}

