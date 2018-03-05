package com.remote.banking.controllers;

import com.remote.banking.TestApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {TestApplication.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    // TODO: 3/5/2018 for successful testing use embedded database to query and satisfy necessarily dependencies
    public void getUserInfo() throws Exception {
        mockMvc.perform(get("/rest/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN));
//                .andExpect(content().string("Hello, world!"));
    }

    @Test
    public void getUserInfo1() {
    }

    @Test
    public void getUserEmails() {
    }
}