package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.BlockCardCommand;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlockCardUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private BlockCardUseCase blockCardUseCase;

    @Test
    void testBlockCard_Success() {
        // Arrange
        BlockCardCommand command = new BlockCardCommand("1234567890");
        Card mockCard = new Card(1L, "1234567890", "John Doe", LocalDate.now(), BigDecimal.ZERO, Status.ACTIVE, 1L);
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.of(mockCard));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Card blockedCard = blockCardUseCase.blockCard(command);

        // Assert
        assertNotNull(blockedCard);
        assertEquals(Status.BLOCKED, blockedCard.getStatus());
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verify(cardRepository, times(1)).save(mockCard);
    }

    @Test
    void testBlockCard_CardNotFoundException() {
        // Arrange
        BlockCardCommand command = new BlockCardCommand("1234567890");
        when(cardRepository.findByCardNumber("1234567890")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CardNotFoundException.class, () -> blockCardUseCase.blockCard(command));
        verify(cardRepository, times(1)).findByCardNumber("1234567890");
        verifyNoMoreInteractions(cardRepository);
    }
}