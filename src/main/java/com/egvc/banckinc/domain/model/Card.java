package com.egvc.banckinc.domain.model;


import com.egvc.banckinc.domain.exceptions.CardBlockedException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;


@Getter
public class Card {

    private Long cardId;
    private String cardNumber;
    private String cardholderName;
    private LocalDate expirationDate;
    private BigDecimal balance;
    private Status status;
    private Long productId;


    public Card() {
    }

    public Card(Long productId){
        this.productId = productId;
        this.cardNumber = generateRandomNumbers(productId);
        this.expirationDate = LocalDate.now().plusYears(3);
        this.balance = BigDecimal.ZERO;
        this.status = Status.INACTIVE;

    }

    public Card(Long cardId, String cardNumber, String cardholderName, LocalDate expirationDate,
                BigDecimal balance, Status status,  Long productId) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.balance = balance;
        this.status = status;
        this.productId = productId;
    }

    public void activateCard() {
        this.status = Status.ACTIVE;
    }

    public void blockCard() {
        this.status =  Status.BLOCKED;
    }

    public void reloadBalance(BigDecimal amount) {
        if(this.getStatus()!=Status.ACTIVE){
            throw new CardBlockedException();
        }
        this.balance = this.balance.add(amount);
    }

    public void discountBalance(BigDecimal amount) {
        if(this.getStatus()!=Status.ACTIVE){
            throw new CardBlockedException();
        }
        this.balance = this.balance.subtract(amount);
    }


    private String generateRandomNumbers(Long productId) {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        return productId.toString().concat(cardNumber.toString());
    }



}