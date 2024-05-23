package com.egvc.banckinc.aplication.services;

import com.egvc.banckinc.infraestructure.dtos.transaction.request.AnulationTransactionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.PurchaseTransacionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;

public interface TransationService {

    TransactionResponse cancelTransaction(AnulationTransactionDto request);
    TransactionResponse getTransaction(Long transaction);
    TransactionPurchaseResponse transactionPurchase(PurchaseTransacionDto request);

}
