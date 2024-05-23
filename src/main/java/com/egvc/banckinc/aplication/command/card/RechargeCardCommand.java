package com.egvc.banckinc.aplication.command.card;

import java.math.BigDecimal;

public record RechargeCardCommand(
        String cardId,
        BigDecimal rechargeAmount
) {}
