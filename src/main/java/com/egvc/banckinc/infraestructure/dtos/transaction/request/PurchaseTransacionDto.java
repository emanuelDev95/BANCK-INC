package com.egvc.banckinc.infraestructure.dtos.transaction.request;

import java.math.BigDecimal;

public record PurchaseTransacionDto(String cardNumber, BigDecimal price){
}
