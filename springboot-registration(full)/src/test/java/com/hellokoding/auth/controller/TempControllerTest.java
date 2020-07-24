package com.hellokoding.auth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by alireza on 5/4/20.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TempControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    TempController tempController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tempController).build();
    }

    @Test
    void testController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/haaha")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}