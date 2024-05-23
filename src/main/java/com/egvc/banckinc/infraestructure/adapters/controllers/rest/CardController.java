package com.egvc.banckinc.infraestructure.adapters.controllers.rest;

import com.egvc.banckinc.aplication.services.CardService;
import com.egvc.banckinc.infraestructure.dtos.card.request.EnrrollCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.request.RechargeCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping(path = "/{productid}/number")
    public ResponseEntity<CardResponse> getCardNumber(@PathVariable("productid") Long productId){
        return  ResponseEntity.ok(cardService.generateCardNumber(productId));
    }

    @PostMapping("/enroll")
    public ResponseEntity<CardResponse> enrollCard(@RequestBody EnrrollCardRequest request){
        return ResponseEntity.ok(cardService.activateCard(request.cardId()));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable("cardId") String cardId){
        cardService.deactivateCard(cardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/balance")
    public ResponseEntity<CardResponse> postBalance(@RequestBody RechargeCardRequest request){
        return ResponseEntity.ok(cardService.rechargeCard(request));
    }
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<CardBalanceReponse> getBalance(@PathVariable("cardId") String cardId){
        return ResponseEntity.ok(cardService.getBalance(cardId));
    }


}
