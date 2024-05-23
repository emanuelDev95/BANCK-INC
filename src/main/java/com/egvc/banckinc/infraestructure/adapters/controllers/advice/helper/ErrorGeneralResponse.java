package com.egvc.banckinc.infraestructure.adapters.controllers.advice.helper;

public record ErrorGeneralResponse(
        Integer statusCode,
        String message
) {
}
