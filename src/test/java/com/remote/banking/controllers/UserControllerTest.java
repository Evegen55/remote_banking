package com.remote.banking.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// TODO: 3/5/2018 for clearer testing use embedded database to query and satisfy necessarily dependencies
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserInfo() throws Exception {
        mockMvc.perform(get("/rest/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // TODO: 3/5/2018 add cases
//                .andExpect(content().string("Hello, world!"));
    }

    @Test
    public void getUserInfo1() throws Exception {
        mockMvc.perform(get("/rest/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // TODO: 3/5/2018 add cases
    }

    @Test
    public void getUserEmails() throws Exception {
        mockMvc.perform(get("/rest/v1/users/1/emails"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // TODO: 3/5/2018 add cases
    }
}