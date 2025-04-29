package com.contribhub.contribhubbackend.controller;

import com.contribhub.contribhubbackend.model.GitHubUser;
import com.contribhub.contribhubbackend.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    GitHubService gitHubService;

    @GetMapping("/{username}")
    public GitHubUser getGitHubData(@PathVariable String username) {
        return gitHubService.fetchGitHubUserData(username);
    }

    @GetMapping("/dashboard/{username}")
    public GitHubUser getDashboardData(@PathVariable String username) {
        return gitHubService.getGitHubUserFromDatabase(username);
    }
}
