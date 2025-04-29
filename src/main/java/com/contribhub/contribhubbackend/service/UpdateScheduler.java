package com.contribhub.contribhubbackend.service;

import com.contribhub.contribhubbackend.model.GitHubUser;
import com.contribhub.contribhubbackend.model.StackOverflowUser;
import com.contribhub.contribhubbackend.repository.GitHubUserRepository;
import com.contribhub.contribhubbackend.repository.StackOverflowUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateScheduler {

    @Autowired
    GitHubService gitHubService;

    @Autowired
    StackOverflowService stackOverflowService;

    @Autowired
    GitHubUserRepository gitHubUserRepository;

    @Autowired
    StackOverflowUserRepository stackOverflowUserRepository;

    @Scheduled(fixedRate = 60000)
    public void updateAllUserData() {
        System.out.println("Scheduled update started...");

        List<GitHubUser> gitHubUsers = gitHubUserRepository.findAll();
        for (GitHubUser gitHubUser : gitHubUsers) {
            gitHubService.fetchGitHubUserData(gitHubUser.getUsername());
        }

        List<StackOverflowUser> stackOverflowUsers = stackOverflowUserRepository.findAll();
        for (StackOverflowUser stackOverflowUser : stackOverflowUsers) {
            stackOverflowService.fetchStackOverflowUserData(stackOverflowUser.getStackOverflowUserId());
        }

        System.out.println("Scheduled update completed.");
    }
}
