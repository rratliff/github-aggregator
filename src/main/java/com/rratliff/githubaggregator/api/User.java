package com.rratliff.githubaggregator.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record User(
        String userName,
        String displayName,
        String avatar,
        String geoLocation,
        String email,
        String url,
        LocalDateTime createdAt,
        List<Repository> repos
) {}
