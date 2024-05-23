package com.egvc.banckinc.infraestructure.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "card")
@Data
@Builder @NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCard;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductCardEntity productCard;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;


    @Column(name = "card_status")
    private String cardStatus;

    @Column(name = "balance")
    private BigDecimal balance;

}

