package com.rratliff.githubaggregator.api;

import com.rratliff.githubaggregator.github.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
        return this.githubService.getUser(username);
    }
}
