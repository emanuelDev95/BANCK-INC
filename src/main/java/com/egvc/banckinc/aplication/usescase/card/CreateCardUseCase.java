package com.egvc.banckinc.aplication.usescase.card;

import com.egvc.banckinc.domain.exceptions.ProductNotFoundException;
import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.aplication.command.card.CreateCardCommand;
import com.egvc.banckinc.domain.repository.CardRepository;
import com.egvc.banckinc.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCardUseCase {

    private final CardRepository cardRepository;
    private final ProductRepository productRepository;

    public Card createCard(CreateCardCommand createCardCommand){
        productRepository.findById(createCardCommand.productId())
                .orElseThrow(ProductNotFoundException::new);

        var card = new Card(createCardCommand.productId());

        return cardRepository.save(card);
    }
}
