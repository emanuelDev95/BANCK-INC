package com.egvc.banckinc.domain.repository;

import com.egvc.banckinc.domain.model.Card;

import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    Optional<Card> findById(Long id);
    Optional <Card> findByCardNumber(String cardNumber);



}

