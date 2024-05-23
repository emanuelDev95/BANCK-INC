package com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper;

import com.egvc.banckinc.domain.model.Currency;
import com.egvc.banckinc.domain.model.StatusTransaction;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionToDtoTest {

    @Mock
    private Transaction transaction;

    @InjectMocks
    private TransactionToDto transactionToDto;

    @Test
    void testToDto() {
        when(transaction.getCardNumber()).thenReturn("1234567890123456");
        when(transaction.getAmount()).thenReturn(BigDecimal.valueOf(100));
        when(transaction.getDescription()).thenReturn("Test Transaction");
        when(transaction.getCurrency()).thenReturn(Currency.USD);

        TransactionResponse transactionResponse = transactionToDto.toDto(transaction);

        assertEquals("1234567890123456", transactionResponse.cardNumber());
        assertEquals(BigDecimal.valueOf(100), transactionResponse.amount());
        assertEquals("Test Transaction", transactionResponse.description());
        assertEquals("USD", transactionResponse.currency());
    }

    @Test
    void testToPurchaseDto() {
        when(transaction.getCardNumber()).thenReturn("1234567890123456");
        when(transaction.getAmount()).thenReturn(BigDecimal.valueOf(100));
        when(transaction.getDescription()).thenReturn("Test Transaction");
        when(transaction.getCurrency()).thenReturn(Currency.USD);
        when(transaction.getStatus()).thenReturn(StatusTransaction.ACTIVE);

        TransactionPurchaseResponse purchaseResponse = transactionToDto.toPurchaseDto(transaction);

        assertEquals("1234567890123456", purchaseResponse.cardNumber());
        assertEquals(BigDecimal.valueOf(100), purchaseResponse.amount());
        assertEquals("Test Transaction", purchaseResponse.description());
        assertEquals("USD", purchaseResponse.currency());
        assertEquals("ACTIVE", purchaseResponse.status());
    }
}