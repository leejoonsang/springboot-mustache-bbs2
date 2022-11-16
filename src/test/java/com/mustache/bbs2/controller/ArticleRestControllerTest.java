package com.mustache.bbs2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs2.domain.dto.ArticleAddRequest;
import com.mustache.bbs2.domain.dto.ArticleAddResponse;
import com.mustache.bbs2.domain.dto.ArticleDto;
import com.mustache.bbs2.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ArticleService articleService;

    @Test
    void findSingle() throws Exception {
        Long id = 21L;
        given(articleService.getArticleById(id)).willReturn(new ArticleDto(21L, "제목21", "내용21"));

        mockMvc.perform(get("/api/v1/articles/21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(id);
    }

    @Test
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목임", "내용임");

        given(articleService.add(any())).willReturn(new ArticleAddResponse(21L, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목", "내용"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).add(any());
    }
}