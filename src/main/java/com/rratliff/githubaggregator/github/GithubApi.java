package com.rratliff.githubaggregator.github;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GithubApi {

    private final String baseUrl;

    public GithubApi(@Value("${github.baseUrl:https://api.github.com}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public User getUser(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(baseUrl+"/users/"+username)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(User.class);
    }

    public JsonNode getRepositories(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(baseUrl+"/users/"+username+"/repos")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(JsonNode.class);
    }
}
