package com.kucw.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .header("Authorization", "Basic dGVzdDFAZ21haWwuY29tOjExMQ==");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void testHello2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .with(httpBasic("test1@gmail.com", "111"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void testWelcome() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/welcome")
                .with(httpBasic("test1@gmail.com", "111"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void testWelcome2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/welcome")
                .with(httpBasic("test2@gmail.com", "222"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));
    }

    @WithMockUser(username = "mock", roles = { "NORMAL_MEMBER" })
    @Test
    public void testWelcome3() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/welcome");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));
    }
}
