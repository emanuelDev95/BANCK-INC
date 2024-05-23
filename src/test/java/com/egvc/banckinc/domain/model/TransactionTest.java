package com.egvc.banckinc.domain.model;

import com.egvc.banckinc.domain.exceptions.CardBlockedException;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testCreateTransaction() {
        Card card =  new Card(1L,
                "5456456456",
                "pepo",
                LocalDateTime.now().plusDays(30).toLocalDate(),// Assuming the card expires in 30 days
                BigDecimal.ZERO,
                Status.INACTIVE,
                5L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(1000)); // Assuming initial balance is 1000

        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        Transaction transaction = Transaction.createTransaction(card, transactionAmount);

        assertEquals(transactionAmount, transaction.getAmount());
        assertEquals(card.getCardId(), transaction.getCardId());
        assertEquals(card.getCardNumber(), transaction.getCardNumber());
        assertEquals(StatusTransaction.ACTIVE, transaction.getStatus());
    }

    @Test
    void testCreateTransactionWithBlockedCard() {
        Card card = new Card(123L);
        card.blockCard();

        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        assertThrows(CardBlockedException.class, () -> Transaction.createTransaction(card, transactionAmount));
    }

    @Test
    void testCreateTransactionWithExpiredCard() {
        Card card = new Card(1L,
                "5456456456",
                "pepo",
                LocalDateTime.now().minusDays(1).toLocalDate(),// Card expired yesterday
                BigDecimal.ZERO,
                Status.BLOCKED,
                5L);

                card.activateCard();


        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        assertThrows(CardBlockedException.class, () -> Transaction.createTransaction(card, transactionAmount));
    }

    @Test
    void testCreateTransactionWithInsufficientBalance() {
        Card card = new Card(123L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(100)); // Assuming initial balance is 100

        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        assertThrows(CardNotFoundException.class, () -> Transaction.createTransaction(card, transactionAmount));
    }

    @Test
     void testCancelTransactionWithin24Hours() {
        // Arrange
        Card card = new Card(123L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(100));
        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        Transaction transaction = new Transaction(1L,
                LocalDateTime.now().plusHours(6),// Simulate 25 hours passing
                transactionAmount,
                "asadsasd",
                Currency.USD,
                "232323",
                StatusTransaction.ACTIVE
        );

        // Act
        transaction.cancel(card);

        // Assert
        assertEquals(StatusTransaction.ANULED, transaction.getStatus());
        // Add more assertions as needed
    }

    @Test
     void testCancelTransactionAfter24Hours() {
        // Arrange
        Card card = new Card(123L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(100));
        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        Transaction transaction = new Transaction(1L,
                LocalDateTime.now().minusHours(25),// Simulate 25 hours passing
                transactionAmount,
                "asadsasd",
                Currency.USD,
                "232323",
                StatusTransaction.ACTIVE
                );




        // Act and Assert
        assertThrows(IllegalStateException.class, () -> {
            transaction.cancel(card);
        });
    }

    @Test
    void createTransactionTest(){

        Card card = new Card(123L);
        card.activateCard();
        card.reloadBalance(BigDecimal.valueOf(100));
        BigDecimal transactionAmount = BigDecimal.valueOf(500);
        Transaction transaction = new Transaction(1L,
                transactionAmount,
                "asdqwe",
                Currency.USD,
                "748273847923"
        );


        assertEquals(StatusTransaction.ACTIVE, transaction.getStatus());
        assertNotNull(transaction.getTransactionId());
        assertNotNull(transaction.getTransactionId());
        assertNotNull(transaction.getAmount(), String.valueOf(transaction.getAmount()));
        assertNotNull(transaction.getDescription());
        assertNotNull(transaction.getCurrency());
        assertNotNull(transaction.getCardNumber());




    }

}