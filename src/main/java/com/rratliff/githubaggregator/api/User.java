package com.rratliff.githubaggregator.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.ZonedDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record User(
        String userName,
        String displayName,
        String avatar,
        String geoLocation,
        String email,
        String url,
        /** Tue, 25 Jan 2011 18:44:36 GMT */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss zzz")
        ZonedDateTime createdAt,
        List<Repository> repos
) {}
