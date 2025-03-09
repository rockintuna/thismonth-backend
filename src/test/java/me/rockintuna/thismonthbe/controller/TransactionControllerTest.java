package me.rockintuna.thismonthbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.rockintuna.thismonthbe.dto.TransactionResponseDto;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;
import me.rockintuna.thismonthbe.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getTransactions() throws Exception {
        //given
        int year = 2025;
        int month = 3;
        String userName = "tester";
        List<TransactionResponseDto> list = new ArrayList<>();
        list.add(new TransactionResponseDto("월급", 1000000L, userName));
        list.add(new TransactionResponseDto("교통비", -100000L, userName));
        list.add(new TransactionResponseDto("용돈", -200000L, userName));
        given(transactionService.getTransactions(year, month)).willReturn(list);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions")
                        .param("year", String.valueOf(year))
                        .param("month", String.valueOf(month)))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].amount").isNumber());
    }

    @Test
        void addTransaction() throws Exception {
            //given
            int year = 2025;
            int month = 3;
            TransactionRequestDto requestDto = new TransactionRequestDto(
                    "월급", 1000000L, year, month, 1L
            );
            String requestBody = objectMapper.writeValueAsString(requestDto);

            //when
            mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                            .contentType("application/json")
                            .content(requestBody))
                    //then
                    .andExpect(status().isOk());
    }

    @Test
    void addTransactionWithMonthValidError() throws Exception {
        //given
        int year = 2025;
        int month = 13;
        TransactionRequestDto requestDto = new TransactionRequestDto(
                "월급", 1000000L, year, month, 1L
        );
        String requestBody = objectMapper.writeValueAsString(requestDto);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType("application/json")
                        .content(requestBody))
                //then
                .andExpect(status().isBadRequest());
    }

}