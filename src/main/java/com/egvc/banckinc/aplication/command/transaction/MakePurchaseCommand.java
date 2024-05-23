package com.egvc.banckinc.aplication.command.transaction;

import java.math.BigDecimal;

public record MakePurchaseCommand(
        String cardId,
        BigDecimal purchaseAmount
) {}
