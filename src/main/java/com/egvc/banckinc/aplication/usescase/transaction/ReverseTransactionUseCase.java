package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.command.transaction.ReverseTransactionCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.exceptions.TransactionNotFoundException;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.domain.repository.CardRepository;
import com.egvc.banckinc.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReverseTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public Transaction reverseTransaction(ReverseTransactionCommand command){

        var transaction = transactionRepository.getTransationById(Long.parseLong(command.transactionId()))
                .orElseThrow(TransactionNotFoundException::new);

        var card = cardRepository.findByCardNumber(command.cardId())
                        .orElseThrow(CardNotFoundException::new);


        transaction.cancel(card);

        cardRepository.save(card);

        var transactionSved =  transactionRepository.save(transaction);

        return transactionSved;

    }
}

