package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.BlockCardCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockCardUseCase {

    private final CardRepository cardRepository;

    public Card blockCard(BlockCardCommand command) {

        var card = cardRepository.findByCardNumber(command.cardId())
                .orElseThrow(CardNotFoundException::new);

        card.blockCard();

        return cardRepository.save(card);


    }
}
