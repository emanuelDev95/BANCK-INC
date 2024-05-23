package com.egvc.banckinc.infraestructure.adapters.controllers.rest;

import com.egvc.banckinc.aplication.services.TransationService;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.AnulationTransactionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.PurchaseTransacionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransationService transationService;

    @PostMapping("/purchase")
    public ResponseEntity<TransactionPurchaseResponse> purchase(@RequestBody PurchaseTransacionDto purchaseDto) {
        return ResponseEntity.ok(transationService.transactionPurchase(purchaseDto));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transationService.getTransaction(transactionId));
    }

    @PostMapping("/anulation")
    public ResponseEntity<TransactionResponse> anulation(@RequestBody AnulationTransactionDto anulationTransactionDto){
        return ResponseEntity.ok(transationService.cancelTransaction(anulationTransactionDto));
    }
}
