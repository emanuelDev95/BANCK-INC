package com.egvc.banckinc.aplication.services.impl;

import com.egvc.banckinc.aplication.command.transaction.MakePurchaseCommand;
import com.egvc.banckinc.aplication.command.transaction.ReverseTransactionCommand;
import com.egvc.banckinc.aplication.query.transaction.TransactionQuery;
import com.egvc.banckinc.aplication.services.TransationService;
import com.egvc.banckinc.aplication.usescase.transaction.GetTransactionUseCase;
import com.egvc.banckinc.aplication.usescase.transaction.MakePurchaseUseCase;
import com.egvc.banckinc.aplication.usescase.transaction.ReverseTransactionUseCase;
import com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper.TransactionToDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.AnulationTransactionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.PurchaseTransacionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransationService {

    private final ReverseTransactionUseCase reverseTransactionUseCase;
    private final GetTransactionUseCase getTransactionUseCase;
    private final MakePurchaseUseCase makePurchaseUseCase;


    private final TransactionToDto transactionToDto;
    @Override
    public TransactionResponse cancelTransaction(AnulationTransactionDto request) {
        var transaction =  reverseTransactionUseCase.reverseTransaction(new ReverseTransactionCommand(request.cardId(),
                request.transactionId()
        ));

        return transactionToDto.toDto(transaction);
    }

    @Override
    public TransactionResponse getTransaction(Long id) {

        var transaction =  getTransactionUseCase.getTransaction(new TransactionQuery(id));
        return transactionToDto.toDto(transaction);
    }

    @Override
    public TransactionPurchaseResponse transactionPurchase(PurchaseTransacionDto request) {
        var transaction = makePurchaseUseCase.makePurchase(new MakePurchaseCommand(request.cardNumber(), request.price()));
        return transactionToDto.toPurchaseDto(transaction
        );
    }
}
