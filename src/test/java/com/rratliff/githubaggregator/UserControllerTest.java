package com.rratliff.githubaggregator;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Test
    void user_withUsername_returnsOk(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/octocat"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Octocat")));
    }
}
