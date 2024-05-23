package com.egvc.banckinc.aplication.services;

import com.egvc.banckinc.infraestructure.dtos.card.request.RechargeCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;

public interface CardService {

        CardResponse generateCardNumber(Long productId);
        CardResponse activateCard(String cardId);
        void deactivateCard(String cardId);
        CardResponse rechargeCard(RechargeCardRequest request);
        CardBalanceReponse getBalance(String cardId);

}
