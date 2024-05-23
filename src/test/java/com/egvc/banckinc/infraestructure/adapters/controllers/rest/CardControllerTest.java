package com.egvc.banckinc.infraestructure.adapters.controllers.rest;

import com.egvc.banckinc.aplication.services.CardService;
import com.egvc.banckinc.infraestructure.dtos.card.request.EnrrollCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.request.RechargeCardRequest;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardBalanceReponse;
import com.egvc.banckinc.infraestructure.dtos.card.response.CardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    void testGetCardNumber() throws Exception {
        CardResponse mockResponse = new CardResponse(123L, "1234567890");
        when(cardService.generateCardNumber(123L)).thenReturn(mockResponse);

        mockMvc.perform(get("/card/123/number")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(123L))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"));

        verify(cardService, times(1)).generateCardNumber(123L);
    }

    @Test
    void testEnrollCard() throws Exception {
        EnrrollCardRequest request = new EnrrollCardRequest("123");

        CardResponse mockResponse = new CardResponse(123L, "1234567890");
        when(cardService.activateCard("123")).thenReturn(mockResponse);

        mockMvc.perform(post("/card/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardId\":123}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(123L))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"));

        verify(cardService, times(1)).activateCard("123");
    }

    @Test
    void testDeleteCard() throws Exception {

        doNothing().when(cardService).deactivateCard("123");


        mockMvc.perform(delete("/card/123")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        verify(cardService, times(1)).deactivateCard("123");
    }

    @Test
    void testPostBalance() throws Exception {
        RechargeCardRequest request = new RechargeCardRequest("123", "5000");


        CardResponse mockResponse = new CardResponse(123L, "1234567890");
        when(cardService.rechargeCard(request)).thenReturn(mockResponse);

        mockMvc.perform(post("/card/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"cardId\": \"123\",\n" +
                                "  \"balance\": \"5000\"\n" +
                                "}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(123L))
                .andExpect(jsonPath("$.cardNumber").value("1234567890"));

        verify(cardService, times(1)).rechargeCard(request);
    }

    @Test
    void testGetBalance() throws Exception {
        CardBalanceReponse mockResponse = new CardBalanceReponse("1234567890", BigDecimal.valueOf(100));
        when(cardService.getBalance("123")).thenReturn(mockResponse);

        mockMvc.perform(get("/card/balance/123")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cardId").value("1234567890"))
                .andExpect(jsonPath("$.balance").value(100));

        verify(cardService, times(1)).getBalance("123");
    }



}