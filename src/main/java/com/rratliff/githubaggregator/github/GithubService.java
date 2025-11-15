package com.rratliff.githubaggregator.github;

import com.rratliff.githubaggregator.api.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/** Calls the GithubAPI class, aggregates and caches the results. */
@Component
public class GithubService {

    private final GithubApi githubApi;

    public GithubService(@Autowired GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public com.rratliff.githubaggregator.api.User getUser(String username) {
        final var user = githubApi.getUser(username);
        final var repositories = githubApi.getRepositories(username);
        final var repos = new ArrayList<Repository>();
        if (repositories.isArray()) {
            repositories.forEach(node -> repos.add(new Repository(node.get("name").asText(), node.get("url").asText())));
        }
        return new com.rratliff.githubaggregator.api.User(
                user.login(),
                user.name(),
                user.avatarUrl(),
                user.location(),
                user.email(),
                user.url(),
                user.createdAt(),
                repos);
    }
}
