package com.rratliff.githubaggregator;

import com.rratliff.githubaggregator.api.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.rratliff.githubaggregator.api.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{username}")
    @ResponseBody
    public User user(String username) {
        return new User("octocat",
                "The Octocat",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "San Francisco",
                null,
                "https://api.github.com/users/octocat",
                LocalDateTime.of(2025,1, 25, 18, 44, 36),
                List.of(new Repository("boysenberry-repo-1", "https://api.github.com/repos/octocat/boysenberry-repo-1"))
        );
    }
}
