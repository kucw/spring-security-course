package com.kucw.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucw.security.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CsrfTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void watchFreeMovie_noCsrfToken_fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchFreeMovie")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));
    }

    @Test
    public void watchFreeMovie_withCsrfToken_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchFreeMovie")
                .with(httpBasic("normal@gmail.com", "normal"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void register_noCsrfToken_success() throws Exception {
        Member member = new Member();
        member.setEmail("csrfTest@gmail.com");
        member.setPassword("csrfTest");
        member.setName("Csrf Test");
        member.setAge(35);

        String json = objectMapper.writeValueAsString(member);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .header("Content-Type", "application/json")
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void userLogin_noCsrfToken_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/userLogin")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }
}
