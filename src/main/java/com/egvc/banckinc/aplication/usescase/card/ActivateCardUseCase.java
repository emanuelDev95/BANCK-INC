package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.ActivateCardCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivateCardUseCase{

    private final CardRepository cardRepository;


    public Card activateCard(ActivateCardCommand command){

        var card = cardRepository.findByCardNumber(command.cardId())
                .orElseThrow(CardNotFoundException::new);
        card.activateCard();
        return cardRepository.save(card);

    }
}
