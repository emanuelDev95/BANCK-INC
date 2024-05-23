package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.command.card.RechargeCardCommand;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RechargeCardUseCase {

    private final CardRepository cardRepository;

    public Card rechargeCard(RechargeCardCommand command){

        var card = cardRepository.findByCardNumber(command.cardId())
                .orElseThrow(CardNotFoundException::new);

        card.reloadBalance(command.rechargeAmount());

        return cardRepository.save(card);
    }
}

