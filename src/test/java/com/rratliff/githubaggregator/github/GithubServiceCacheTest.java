package com.rratliff.githubaggregator.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rratliff.githubaggregator.api.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GithubServiceCacheTest {

    @MockitoBean
    private GithubApi githubApi;

    @Autowired
    private GithubService githubService;

    @Test
    void getUser_calledTwice_onlyHitsApiOnce() throws Exception {
        var githubUser = new com.rratliff.githubaggregator.github.User(
                "octocat", "https://avatars.githubusercontent.com/u/583231?v=4",
                "https://api.github.com/users/octocat", "The Octocat",
                "San Francisco", null, ZonedDateTime.now());
        var emptyRepos = new ObjectMapper().createArrayNode();
        when(githubApi.getUser("octocat")).thenReturn(githubUser);
        when(githubApi.getRepositories("octocat")).thenReturn(emptyRepos);

        User first = githubService.getUser("octocat");
        User second = githubService.getUser("octocat");

        verify(githubApi, times(1)).getUser("octocat");
        verify(githubApi, times(1)).getRepositories("octocat");
        assertThat(second).isSameAs(first);
    }
}
