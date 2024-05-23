package com.egvc.banckinc.aplication.services.impl;

import com.egvc.banckinc.aplication.command.card.ActivateCardCommand;
import com.egvc.banckinc.aplication.command.card.BlockCardCommand;
import com.egvc.banckinc.aplication.command.card.CreateCardCommand;
import com.egvc.banckinc.aplication.command.card.RechargeCardCommand;
import com.egvc.banckinc.aplication.query.card.BalanceQuery;
import com.egvc.banckinc.aplication.services.CardService;
import com.egvc.banckinc.aplication.usescase.card.*;
import com.egvc.banckinc.infraestructure.adapters.controllers.rest.helper.CardToDto;
import com.egvc.banckinc.infraestructure.dtos.card.request.RechargeCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImp implements CardService {

    private final CreateCardUseCase createCardUseCase;
    private final ActivateCardUseCase activateCardUseCase;
    private final BlockCardUseCase blockCardUseCase;
    private final RechargeCardUseCase rechargeCardUseCase;
    private final GetBalanceUseCase getBalanceUseCase;


    private final CardToDto cardToDto;

    @Override
    public CardResponse generateCardNumber(Long productId) {
        var command = new CreateCardCommand(productId);
        var card = createCardUseCase.createCard(command);
        return  this.cardToDto.toCardResponse(card) ;
    }

    @Override
    public CardResponse activateCard(String cardId) {
        var command = new ActivateCardCommand(cardId);
        var card = activateCardUseCase.activateCard(command);
        return this.cardToDto.toCardResponse(card);
    }

    @Override
    public void deactivateCard(String cardId) {

        var command = new BlockCardCommand(cardId);
        blockCardUseCase.blockCard(command);

    }

    @Override
    public CardResponse rechargeCard(RechargeCardRequest request) {

        var command = new RechargeCardCommand(request.cardId(),
                BigDecimal.valueOf(Long.parseLong(request.balance())));
        var card  = rechargeCardUseCase.rechargeCard(command);
        return this.cardToDto.toCardResponse(card);
    }

    @Override
    public CardBalanceReponse getBalance(String cardId) {

        var command = new BalanceQuery(cardId);
        var card = getBalanceUseCase.getBalance(command);
        return this.cardToDto.toCardBalanceReponse(card);
    }
}
