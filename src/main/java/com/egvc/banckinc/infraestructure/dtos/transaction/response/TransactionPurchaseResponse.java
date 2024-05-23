package com.egvc.banckinc.infraestructure.dtos.transaction.response;

import java.math.BigDecimal;

public record TransactionPurchaseResponse(String cardNumber, BigDecimal amount, String description, String currency, String status) {
}
