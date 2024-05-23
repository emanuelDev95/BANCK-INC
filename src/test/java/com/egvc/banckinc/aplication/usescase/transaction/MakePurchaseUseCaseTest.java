package com.egvc.banckinc.aplication.usescase.transaction;

import com.egvc.banckinc.aplication.command.transaction.MakePurchaseCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
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
class MakePurchaseUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private MakePurchaseUseCase makePurchaseUseCase;

    @Test
    void testMakePurchase_Success() {
        // Arrange
        MakePurchaseCommand command = new MakePurchaseCommand("1234567890", BigDecimal.valueOf(50));
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.valueOf(100), Status.ACTIVE, 1L);
        Transaction mockTransaction = new Transaction(1L, LocalDateTime.now(), BigDecimal.valueOf(50), "Test Transaction", Currency.USD, "1234567890", StatusTransaction.REALIZED);
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Transaction result = makePurchaseUseCase.makePurchase(command);

        // Assert
        assertNotNull(result);
        assertEquals(mockTransaction.getCardNumber(), result.getCardNumber());
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verify(cardRepository, times(1)).save(mockCard);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testMakePurchase_CardNotFoundException() {
        // Arrange
        MakePurchaseCommand command = new MakePurchaseCommand("1234567890", BigDecimal.valueOf(50));
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> makePurchaseUseCase.makePurchase(command));
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verifyNoMoreInteractions(cardRepository, transactionRepository);
    }
}
