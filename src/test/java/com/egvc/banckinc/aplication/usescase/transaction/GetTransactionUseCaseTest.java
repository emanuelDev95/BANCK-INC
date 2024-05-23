package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.query.transaction.TransactionQuery;
import com.egvc.banckinc.domain.exceptions.TransactionNotFoundException;
import com.egvc.banckinc.domain.model.Currency;
import com.egvc.banckinc.domain.model.StatusTransaction;
import com.egvc.banckinc.domain.model.Transaction;
import com.egvc.banckinc.domain.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private GetTransactionUseCase getTransactionUseCase;

    @Test
    void testGetTransaction_Success() {
        // Arrange
        TransactionQuery query = new TransactionQuery(1L);
        Transaction mockTransaction = new Transaction(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "Test Transaction", Currency.USD, "1234567890", StatusTransaction.REALIZED);
        when(transactionRepository.getTransationById(1L)).thenReturn(Optional.of(mockTransaction));

        // Act
        Transaction result = getTransactionUseCase.getTransaction(query);

        // Assert
        assertNotNull(result);
        assertEquals(mockTransaction, result);
        verify(transactionRepository, times(1)).getTransationById(1L);
    }

    @Test
    void testGetTransaction_TransactionNotFoundException() {
        // Arrange
        TransactionQuery query = new TransactionQuery(1L);
        when(transactionRepository.getTransationById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TransactionNotFoundException.class, () -> getTransactionUseCase.getTransaction(query));
        verify(transactionRepository, times(1)).getTransationById(1L);
    }
}
