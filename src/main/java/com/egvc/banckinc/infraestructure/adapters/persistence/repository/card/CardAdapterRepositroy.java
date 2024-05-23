package com.egvc.banckinc.infraestructure.adapters.persistence.repository.card;

import com.egvc.banckinc.domain.model.Card;
import com.egvc.banckinc.domain.repository.CardRepository;
import com.egvc.banckinc.infraestructure.adapters.persistence.helper.CardMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class CardAdapterRepositroy implements CardRepository {

    private final CardMapper cardMapper;
    private final CardJpaRepository cardJpaRepository;

    @Override
    public Card save(Card card) {
        var cardEntity = cardMapper.toEntity(card);

       cardJpaRepository.saveAndFlush(cardEntity);
        return cardMapper.toData(cardEntity) ;
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardJpaRepository.findById(id)
                .map(cardMapper::toData);


    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return cardJpaRepository.findByCardNumber(cardNumber)
                .map(cardMapper::toData);
    }
}
