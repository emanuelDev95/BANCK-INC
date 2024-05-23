package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.command.transaction.ReverseTransactionCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.exceptions.TransactionNotFoundException;
import com.egvc.banckinc.domain.model.*;
import com.egvc.banckinc.domain.repository.CardRepository;
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
class ReverseTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private ReverseTransactionUseCase reverseTransactionUseCase;

    @Test
    void testReverseTransaction_Success() {
        // Arrange
        ReverseTransactionCommand command = new ReverseTransactionCommand("1234567890", "1");
        Transaction mockTransaction = new Transaction(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "Test Transaction", Currency.USD, "1234567890", StatusTransaction.ACTIVE);
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.valueOf(100), Status.ACTIVE, 1L);
        when(transactionRepository.getTransationById(1L)).thenReturn(Optional.of(mockTransaction));
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Transaction result = reverseTransactionUseCase.reverseTransaction(command);

        // Assert
        assertNotNull(result);
        assertEquals(mockTransaction, result);
        verify(transactionRepository, times(1)).getTransationById(1L);
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verify(cardRepository, times(1)).save(mockCard);
        verify(transactionRepository, times(1)).save(mockTransaction);
    }

    @Test
    void testReverseTransaction_TransactionNotFoundException() {
        // Arrange
        ReverseTransactionCommand command = new ReverseTransactionCommand("1234567890", "1");
        when(transactionRepository.getTransationById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TransactionNotFoundException.class, () -> reverseTransactionUseCase.reverseTransaction(command));
        verify(transactionRepository, times(1)).getTransationById(1L);
        verifyNoMoreInteractions(transactionRepository, cardRepository);
    }

    @Test
    void testReverseTransaction_CardNotFoundException() {
        // Arrange
        ReverseTransactionCommand command = new ReverseTransactionCommand("1234567890", "1");
        Transaction mockTransaction = new Transaction(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "Test Transaction", Currency.USD, "1234567890", StatusTransaction.REALIZED);
        when(transactionRepository.getTransationById(1L)).thenReturn(Optional.of(mockTransaction));
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> reverseTransactionUseCase.reverseTransaction(command));
        verify(transactionRepository, times(1)).getTransationById(1L);
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verifyNoMoreInteractions(transactionRepository, cardRepository);
    }
}
