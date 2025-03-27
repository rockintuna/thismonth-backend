package me.rockintuna.thismonthbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.rockintuna.thismonthbe.dto.MemoRequestDto;
import me.rockintuna.thismonthbe.dto.MemoResponseDto;
import me.rockintuna.thismonthbe.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MemoController.class)
class MemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemoService memoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getMemos() throws Exception {

        //given
        int year = 2025;
        int month = 12;

        List<MemoResponseDto> list = new ArrayList<>();
        list.add(new MemoResponseDto(1L, "test1", year, month, "정인"));
        list.add(new MemoResponseDto(2L, "test2", year, month, "정인"));
        list.add(new MemoResponseDto(3L, "test3", year, month, "수진"));
        list.add(new MemoResponseDto(4L, "test4", year, month, "수진"));
        given(memoService.getMemos(year, month)).willReturn(list);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/memos")
                .param("year", String.valueOf(year))
                .param("month", String.valueOf(month)))

                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].content").isString())
                .andExpect(jsonPath("$[0].year").isNumber())
                .andExpect(jsonPath("$[0].month").isNumber())
                .andExpect(jsonPath("$[0].userName").isString());
    }

    @Test
    void createMemo() throws Exception {
        //given
        int year = 2025;
        int month = 12;
        String content = "test";

        MemoRequestDto requestDto = new MemoRequestDto(year, month, content, 1L);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        MemoResponseDto responseDto =
                new MemoResponseDto(1L, content, year, month, "정인");
        given(memoService.createMemo(any(MemoRequestDto.class))).willReturn(responseDto);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/memos")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON))

                //then
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").value(responseDto.getContent()))
                .andExpect(jsonPath("$.year").value(year))
                .andExpect(jsonPath("$.month").value(month));
    }
}