package com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper;

import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardToDtoTest {

    @Mock
    private Card card;

    @InjectMocks
    private CardToDto cardToDto;
    @Test
    void testToCardResponse() {

        when(card.getCardId()).thenReturn(123L);
        when(card.getCardNumber()).thenReturn("1234567890123456");


        CardResponse cardResponse = cardToDto.toCardResponse(card);

        assertEquals(123L, cardResponse.id());
        assertEquals("1234567890123456", cardResponse.cardNumber());
    }

    @Test
    void testToCardBalanceResponse() {

        when(card.getCardNumber()).thenReturn("1234567890123456");
        when(card.getBalance()).thenReturn(BigDecimal.valueOf(1000));


        CardBalanceReponse cardBalanceResponse = cardToDto.toCardBalanceReponse(card);


        assertEquals("1234567890123456", cardBalanceResponse.cardId());
        assertEquals(BigDecimal.valueOf(1000), cardBalanceResponse.balance());
    }

}