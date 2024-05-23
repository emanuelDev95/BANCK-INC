package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.RechargeCardCommand;
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
class RechargeCardUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private RechargeCardUseCase rechargeCardUseCase;

    @Test
    void testRechargeCard_Success() {
        // Arrange
        RechargeCardCommand command = new RechargeCardCommand("1234567890", BigDecimal.valueOf(50));
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.valueOf(100), Status.ACTIVE, 1L);
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Card rechargedCard = rechargeCardUseCase.rechargeCard(command);

        // Assert
        assertNotNull(rechargedCard);
        assertEquals(BigDecimal.valueOf(150), rechargedCard.getBalance());
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testRechargeCard_CardNotFoundException() {
        // Arrange
        RechargeCardCommand command = new RechargeCardCommand("1234567890", BigDecimal.valueOf(50));
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> rechargeCardUseCase.rechargeCard(command));
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verifyNoMoreInteractions(cardRepository);
    }
}
