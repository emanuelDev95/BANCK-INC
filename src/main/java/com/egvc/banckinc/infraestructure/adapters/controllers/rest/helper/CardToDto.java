package com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper;

import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;
import org.springframework.stereotype.Component;

@Component
public class CardToDto {


    public CardResponse toCardResponse(Card card) {
        return new CardResponse(card.getCardId(), card.getCardNumber());
    }
    public CardBalanceReponse toCardBalanceReponse(Card card) {
        return new CardBalanceReponse(card.getCardNumber(), card.getBalance());

    }
}
