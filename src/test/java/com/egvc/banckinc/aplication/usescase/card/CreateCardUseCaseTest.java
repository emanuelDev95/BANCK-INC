package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.CreateCardCommand;
import com.egvc.banckinc.domain.exceptions.ProductNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.model.ProductCard;
import com.egvc.banckinc.domain.repository.CardRepository;
import com.egvc.banckinc.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCardUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateCardUseCase createCardUseCase;

    @Test
    void testCreateCard_Success() {
        // Arrange
        CreateCardCommand createCardCommand = new CreateCardCommand(1L);
        ProductCard mockProduct = new ProductCard(1L, "Test Product");
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Card createdCard = createCardUseCase.createCard(createCardCommand);

        // Assert
        assertNotNull(createdCard);
        assertEquals(createCardCommand.productId(), createdCard.getProductId());
        verify(productRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testCreateCard_ProductNotFoundException() {
        // Arrange
        CreateCardCommand createCardCommand = new CreateCardCommand(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> createCardUseCase.createCard(createCardCommand));
        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(cardRepository);
    }
}