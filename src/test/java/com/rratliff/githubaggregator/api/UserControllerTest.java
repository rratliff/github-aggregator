package com.rratliff.githubaggregator.api;

import com.rratliff.githubaggregator.github.GithubService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockitoBean
    private GithubService githubService;

    @Test
    void user_withUsername_returnsOk(@Autowired MockMvc mvc) throws Exception {
        when(githubService.getUser("octocat")).thenReturn(githubUser());
        mvc.perform(get("/user/octocat"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Octocat")));
    }

    @Test
    void user_withUsername_returnsRepository(@Autowired MockMvc mvc) throws Exception {
        when(githubService.getUser("octocat")).thenReturn(githubUser());
        mvc.perform(get("/user/octocat"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("boysenberry-repo-1")));
    }

    @Test
    void user_withUsername_returnsDateInExpectedFormat(@Autowired MockMvc mvc) throws Exception {
        when(githubService.getUser("octocat")).thenReturn(githubUser());
        mvc.perform(get("/user/octocat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.created_at").value("Tue, 25 Jan 2011 18:44:36 GMT"));
    }

    private User githubUser() {
        return new User("octocat",
                "The Octocat",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "San Francisco",
                null,
                "https://api.github.com/users/octocat",
                ZonedDateTime.of(LocalDateTime.of(2011,1, 25, 18, 44, 36), ZoneId.of("GMT")),
                List.of(new Repository("boysenberry-repo-1", "https://api.github.com/repos/octocat/boysenberry-repo-1"))
        );
    }

}
