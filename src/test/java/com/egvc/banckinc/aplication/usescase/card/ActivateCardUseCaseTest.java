package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.ActivateCardCommand;
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
class ActivateCardUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private ActivateCardUseCase activateCardUseCase;

    @Test
    void testActivateCard_Success() {

        // Arrange
        Card card = new Card(1234567890L);
        ActivateCardCommand command = new ActivateCardCommand("1234567890");
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.ZERO, Status.INACTIVE, 1L);
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));
        when(cardRepository.save(any())).thenReturn(mockCard);


        // Act
        Card activatedCard = activateCardUseCase.activateCard(command);

        // Assert
        assertNotNull(activatedCard);
        assertEquals(Status.ACTIVE, activatedCard.getStatus());
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verify(cardRepository, times(1)).save(mockCard);
    }

    @Test
    void testActivateCard_CardNotFoundException() {
        // Arrange
        ActivateCardCommand command = new ActivateCardCommand("1234567890");
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> activateCardUseCase.activateCard(command));
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verifyNoMoreInteractions(cardRepository);
    }
}