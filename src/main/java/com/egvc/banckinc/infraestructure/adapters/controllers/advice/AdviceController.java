package com.egvc.banckinc.infraestructure.adapters.controllers.advice;

import com.egvc.banckinc.domain.exceptions.*;
import com.egvc.banckinc.infraestructure.adapters.controllers.advice.helper.ErrorGeneralResponse;
import com.egvc.banckinc.infraestructure.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorGeneralResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorGeneralResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()

                ));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorGeneralResponse> handleTransactionNotFound(TransactionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorGeneralResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()

                ));
    }


    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorGeneralResponse> handleCardNotFound(CardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorGeneralResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()

                ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorGeneralResponse> handleProductFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorGeneralResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()

                ));
    }



    @ExceptionHandler(CardBlockedException.class)
    public ResponseEntity<ErrorGeneralResponse> handleCardBlocked(CardBlockedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorGeneralResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()

                ));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorGeneralResponse> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorGeneralResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()

                ));
    }
}
