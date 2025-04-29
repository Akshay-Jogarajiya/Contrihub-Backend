package com.contribhub.contribhubbackend.controller;

import com.contribhub.contribhubbackend.model.GitHubUser;
import com.contribhub.contribhubbackend.model.StackOverflowUser;
import com.contribhub.contribhubbackend.repository.GitHubUserRepository;
import com.contribhub.contribhubbackend.repository.StackOverflowUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping
public class LeaderboardController {


    @Autowired
    GitHubUserRepository gitHubUserRepository;

    @Autowired
    StackOverflowUserRepository stackOverflowUserRepository;

    @GetMapping("/github")
    public List<GitHubUser> getGitHubLeaderboard() {
        List<GitHubUser> users = gitHubUserRepository.findAll();
        users.sort(Comparator.comparingInt(GitHubUser::getFollowers).reversed());
        return users;
    }

    @GetMapping("/stackoverflow")
    public List<StackOverflowUser> getStackOverflowLeaderboard() {
        List<StackOverflowUser> users = stackOverflowUserRepository.findAll();
        users.sort(Comparator.comparingInt(StackOverflowUser::getReputation).reversed());
        return users;
    }
}
