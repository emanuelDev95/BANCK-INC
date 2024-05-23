package com.egvc.banckinc.domain.model;

import com.egvc.banckinc.domain.exceptions.CardBlockedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCreateCard() {
        Card card = new Card(1L,
                "5456456456",
                "pepo",
                LocalDate.now().plusYears(3),
                BigDecimal.ZERO,
                Status.ACTIVE,
                5L);

        assertNotNull(card);
        assertNotNull(card.getCardId());
        assertNotNull(card.getCardNumber());
        assertNotNull(card.getCardholderName());
        assertNotNull(card.getExpirationDate());
        assertNotNull(card.getBalance());
        assertNotNull(card.getStatus());
        assertNotNull(card.getProductId());
    }

    @Test
    void testActivateCard() {
        Card card = new Card();
        card.activateCard();
        assertEquals(Status.ACTIVE, card.getStatus());
    }

    @Test
    void testBlockCard() {
        Card card = new Card();
        card.blockCard();
        assertEquals(Status.BLOCKED, card.getStatus());
    }

    @Test
    void testReloadBalance() {
        Card card = new Card(65656L);
        card.activateCard();
        BigDecimal initialBalance = card.getBalance();
        BigDecimal amountToReload = BigDecimal.valueOf(100);
        card.reloadBalance(amountToReload);
        assertEquals(initialBalance.add(amountToReload), card.getBalance());
    }

    @Test
    void testDiscountBalance() {
        Card card = new Card(65656L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(500)); // Assuming initial balance is 500
        BigDecimal amountToDiscount = BigDecimal.valueOf(200);
        card.discountBalance(amountToDiscount);
        assertEquals(BigDecimal.valueOf(300), card.getBalance());
    }

    @Test
    void testDiscountBalanceThrowsExceptionWhenCardBlocked() {
        Card card = new Card(65656L);
        card.blockCard();
        assertThrows(CardBlockedException.class, () -> card.discountBalance(BigDecimal.valueOf(100)));
    }

    @Test
    void testReloadBalanceThrowsExceptionWhenCardBlocked() {
        Card card = new Card(65656L);
        card.blockCard();
        assertThrows(CardBlockedException.class, () -> card.reloadBalance(BigDecimal.valueOf(100)));
    }



   }