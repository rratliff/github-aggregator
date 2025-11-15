package com.rratliff.githubaggregator.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.ZonedDateTime;

/**
 * {
 *   "login": "octocat",
 *   "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
 *   "url": "https://api.github.com/users/octocat",
 *   "name": "The Octocat",
 *   "location": "San Francisco",
 *   "email": null,
 *   "created_at": "2011-01-25T18:44:36Z",
 * }
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record User(
        String login,
        String avatarUrl,
        String url,
        String name,
        String location,
        String email,
        ZonedDateTime createdAt
) {}
