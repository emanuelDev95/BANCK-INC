package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.query.card.BalanceQuery;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.model.Status;
import com.egvc.banckinc.domain.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetBalanceUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private GetBalanceUseCase getBalanceUseCase;

    @Test
    void testGetBalance_Success() {
        // Arrange
        BalanceQuery query = new BalanceQuery("1234567890");
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.valueOf(100), Status.ACTIVE, 1L);
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));

        // Act
        Card result = getBalanceUseCase.getBalance(query);

        // Assert
        assertNotNull(result);
        assertEquals(mockCard, result);
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
    }

    @Test
    void testGetBalance_CardNotFoundException() {
        // Arrange
        BalanceQuery query = new BalanceQuery("1234567890");
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> getBalanceUseCase.getBalance(query));
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
    }
}