package com.rratliff.githubaggregator.github;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GithubApi {

    private static final String BASE_URL = "https://api.github.com";

    public User getUser(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(BASE_URL+"/users/"+username)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(User.class);
    }

    public JsonNode getRepositories(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(BASE_URL+"/users/"+username+"/repos")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(JsonNode.class);
    }
}
