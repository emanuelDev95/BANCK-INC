package com.egvc.banckinc.infraestructure.adapters.controllers.rest;

import com.egvc.banckinc.aplication.services.TransationService;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.AnulationTransactionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.request.PurchaseTransacionDto;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionPurchaseResponse;
import com.egvc.banckinc.infraestructure.dtos.transaction.response.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransationService transationService;

    @Test
    void testPurchase() throws Exception {
        PurchaseTransacionDto purchaseDto = new PurchaseTransacionDto("1234567890", BigDecimal.valueOf(50) );


        TransactionPurchaseResponse mockResponse = new TransactionPurchaseResponse("1234567890", BigDecimal.valueOf(50), "description", "USD", "REALIZED");
        when(transationService.transactionPurchase(purchaseDto)).thenReturn(mockResponse);

        mockMvc.perform(post("/transaction/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234567890\",\"price\":50}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(50))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.status").value("REALIZED"));

        verify(transationService, times(1)).transactionPurchase(purchaseDto);
    }

    @Test
    void testGetTransaction() throws Exception {
        Long transactionId = 123L;
        TransactionResponse mockResponse = new TransactionResponse("1234567890", BigDecimal.valueOf(50), "description", "USD");
        when(transationService.getTransaction(transactionId)).thenReturn(mockResponse);

        mockMvc.perform(get("/transaction/" + transactionId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(50))
                .andExpect(jsonPath("$.currency").value("USD"));

        verify(transationService, times(1)).getTransaction(transactionId);
    }

    @Test
    void testAnulation() throws Exception {
        AnulationTransactionDto anulationTransactionDto = new AnulationTransactionDto("1234567890", "10");


        TransactionResponse mockResponse = new TransactionResponse("1234567890", BigDecimal.valueOf(50), "description", "USD");
        when(transationService.cancelTransaction(anulationTransactionDto)).thenReturn(mockResponse);

        mockMvc.perform(post("/transaction/anulation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"cardId\": \"1234567890\",\n" +
                                "  \"transactionId\": \"10\"\n" +
                                "}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(50))
                .andExpect(jsonPath("$.currency").value("USD"));

        verify(transationService, times(1)).cancelTransaction(anulationTransactionDto);
    }

}