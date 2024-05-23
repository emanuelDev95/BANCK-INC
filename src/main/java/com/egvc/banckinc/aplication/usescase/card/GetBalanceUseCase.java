package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.aplication.query.card.BalanceQuery;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetBalanceUseCase {

    private final CardRepository cardRepository;

    public Card getBalance(BalanceQuery query) {
        return cardRepository.findByCardNumber(query.cardId())
                .orElseThrow(CardNotFoundException::new);
    }
}
