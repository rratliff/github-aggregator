package com.rratliff.githubaggregator.github;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GithubApi {

    private final String baseUrl;
    private final RestClient restClient;

    public GithubApi(@Value("${github.baseUrl:https://api.github.com}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restClient = RestClient.builder().defaultStatusHandler(
                status -> status.value() == 404, (request, response) -> {
                    throw new UserNotFoundException(response);
                }
        ).build();
    }

    public User getUser(String username) {
        return restClient.get()
                .uri(baseUrl+"/users/"+username)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(User.class);
    }

    public JsonNode getRepositories(String username) {
        return restClient.get()
                .uri(baseUrl+"/users/"+username+"/repos")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(JsonNode.class);
    }
}
