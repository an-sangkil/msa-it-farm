package com.skan.farm.controller;

import com.skan.farm.repository.TestNonConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-15
 */
@TestNonConfiguration
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가
                .alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void signup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/account/sign_up")
                //.requestAttr()
                .param("userId", "skan2")
                .param("password", "password1!")
                .param("userName", "이름1")
                .param("gender", "MAIL")
                .param("email", "sangkil.an@kakao.com")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void signin() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/account/sign_in")
                //.requestAttr()
                .param("userId", "skan")
                .param("password", "password1!")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}