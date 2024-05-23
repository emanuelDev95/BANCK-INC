package com.egvc.banckinc.domain.model;

import com.egvc.banckinc.domain.exceptions.CardBlockedException;
import com.egvc.banckinc.domain.exceptions.CardNotFoundException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Getter
public class Transaction {

    private Long transactionId;
    private LocalDateTime date;
    private BigDecimal amount;
    private String description;
    private Currency currency;
    private String  cardNumber;
    private Long cardId;
    private StatusTransaction status;


    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public Transaction(Long transactionId, BigDecimal amount, String description, Currency currency, String cardNumber) {

        this.transactionId = transactionId;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.description = description;
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.status = StatusTransaction.ACTIVE;
    }

    public Transaction(Long transactionId, LocalDateTime date, BigDecimal amount, String description, Currency currency, String cardNumber, StatusTransaction status) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.status = status;
    }

    public void cancel(Card card) {
        if (status == StatusTransaction.ACTIVE) {
            LocalDateTime now = LocalDateTime.now();
            long hoursDifference = ChronoUnit.HOURS.between(date, now);
            if (hoursDifference <= 24) {
                status = StatusTransaction.ANULED;
                cardId = card.getCardId();
                card.reloadBalance(this.getAmount());

            } else {
                throw new IllegalStateException("Transaction cannot be canceled after 24 hours.");
            }
        } else {
            throw new IllegalStateException("Transaction is already canceled.");
        }
    }

    public static Transaction createTransaction(Card card, BigDecimal amount) {
        var transaction = new Transaction(amount);
        if(!card.getStatus().equals(Status.ACTIVE)) {
            transaction.status = StatusTransaction.CANCELED;
            throw new CardBlockedException();
        }
        if(card.getExpirationDate().isBefore(LocalDate.now())){
            transaction.status = StatusTransaction.CANCELED;
            throw new CardBlockedException();
        }
        if(card.getBalance().compareTo(amount)<0){
            throw new CardNotFoundException();
        }
        card.discountBalance(amount);
        transaction.cardId = card.getCardId();
        transaction.cardNumber = card.getCardNumber();
        transaction.status = StatusTransaction.ACTIVE;
        return transaction;
    }

}
