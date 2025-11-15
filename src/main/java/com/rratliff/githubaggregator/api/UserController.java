package com.rratliff.githubaggregator.api;

import com.rratliff.githubaggregator.github.GithubService;
import com.rratliff.githubaggregator.github.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/user")
public class UserController {

    private final GithubService githubService;

    @Autowired
    public UserController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    @ResponseBody
    public User user(@PathVariable String username) {
        try {
            return this.githubService.getUser(username);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }
}
