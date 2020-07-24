package com.example.gitapi.controller;

import com.example.gitapi.service.GitHubApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alireza on 7/15/20.
 */
@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GitHubApi hubApi;

    @Test
    void getMessage() throws Exception {
        mockMvc.perform(get("/message")).andDo(print()).andExpect(status().isOk());
    }


    @Test
    void g() throws Exception {
        mockMvc.perform(post("/message")
                .param("username", "nader")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }
}