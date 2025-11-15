package com.rratliff.githubaggregator.github;

import org.springframework.http.client.ClientHttpResponse;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(ClientHttpResponse response) {
        super(response.toString());
    }
}
