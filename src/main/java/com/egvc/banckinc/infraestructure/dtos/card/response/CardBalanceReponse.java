package com.egvc.banckinc.infraestructure.dtos.card.response;

import java.math.BigDecimal;

public record CardBalanceReponse(
        String cardId,
        BigDecimal balance
) {
}
