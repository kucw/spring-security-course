package com.kucw.security.controller;

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
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMovies_normalMember_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getMovies")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void watchFreeMovie_normalMember_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchFreeMovie")
                .with(httpBasic("normal@gmail.com", "normal"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void watchFreeMovie_vipMember_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchFreeMovie")
                .with(httpBasic("vip@gmail.com", "vip"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void watchVipMovie_normalMember_fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchVipMovie")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));
    }

    @Test
    public void watchVipMovie_vipMember_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/watchVipMovie")
                .with(httpBasic("vip@gmail.com", "vip"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void uploadMovie_normalMember_vipMember_fail() throws Exception {
        // normal member
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/uploadMovie")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));

        // vip member
        RequestBuilder vipRequestBuilder = MockMvcRequestBuilders
                .post("/uploadMovie")
                .with(httpBasic("vip@gmail.com", "vip"));

        mockMvc.perform(vipRequestBuilder)
                .andExpect(status().is(403));
    }

    @Test
    public void uploadMovie_movieManager_admin_success() throws Exception {
        // movie manager
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/uploadMovie")
                .with(httpBasic("movie-manager@gmail.com", "movie-manager"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));

        // admin
        RequestBuilder adminRequestBuilder = MockMvcRequestBuilders
                .post("/uploadMovie")
                .with(httpBasic("admin@gmail.com", "admin"))
                .with(csrf());

        mockMvc.perform(adminRequestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void deleteMovie_normalMember_vipMember_fail() throws Exception {
        // normal member
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/deleteMovie")
                .with(httpBasic("normal@gmail.com", "normal"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(403));

        // vip member
        RequestBuilder vipRequestBuilder = MockMvcRequestBuilders
                .delete("/deleteMovie")
                .with(httpBasic("vip@gmail.com", "vip"));

        mockMvc.perform(vipRequestBuilder)
                .andExpect(status().is(403));
    }

    @Test
    public void deleteMovie_movieManager_admin_success() throws Exception {
        // movie manager
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/deleteMovie")
                .with(httpBasic("movie-manager@gmail.com", "movie-manager"))
                .with(csrf());

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));

        // admin
        RequestBuilder adminRequestBuilder = MockMvcRequestBuilders
                .delete("/deleteMovie")
                .with(httpBasic("admin@gmail.com", "admin"))
                .with(csrf());

        mockMvc.perform(adminRequestBuilder)
                .andExpect(status().is(200));
    }
}