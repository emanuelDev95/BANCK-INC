package com.egvc.banckinc.infraestructure.dtos.card.request;

public record RechargeCardRequest(
        String cardId,
        String balance
) {
}
