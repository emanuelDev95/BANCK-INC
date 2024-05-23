package com.egvc.banckinc.infraestructure.adapters.persistence.helper;

import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.model.Status;
import com.egvc.banckinc.infraestructure.adapters.helper.MapperGeneric;
import com.egvc.banckinc.infraestructure.adapters.persistence.entities.CardEntity;
import com.egvc.banckinc.infraestructure.adapters.persistence.entities.ProductCardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements MapperGeneric<Card, CardEntity> {
    @Override
    public CardEntity toEntity(Card data) {

        var productCard = new ProductCardEntity();
        productCard.setIdProduct(data.getProductId());


        var card = CardEntity.builder()
                .productCard(productCard)
                .cardNumber(data.getCardNumber())
                .holderName(data.getCardholderName())
                .expirationDate(data.getExpirationDate())
                .cardStatus(data.getStatus().name())
                .balance(data.getBalance())
                .build();

        if(data.getCardId()!= null){
            card.setIdCard(data.getCardId());
        }

        return card;
    }

    @Override
    public Card toData(CardEntity entity) {
        return new Card(entity.getIdCard(),
                entity.getCardNumber(),
                entity.getHolderName(),
                entity.getExpirationDate(),
                entity.getBalance(),
                Status.valueOf(entity.getCardStatus()),
                entity.getProductCard().getIdProduct());
    }
}
