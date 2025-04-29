package com.contribhub.contribhubbackend.service;

import com.contribhub.contribhubbackend.dto.GitHubUserDTO;
import com.contribhub.contribhubbackend.model.GitHubUser;
import com.contribhub.contribhubbackend.repository.GitHubUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    GitHubUserRepository gitHubUserRepository;

    @Autowired
    ObjectMapper objectMapper;

    public GitHubUser fetchGitHubUserData(String username) {
        String apiUrl = "https://api.github.com/users/" +username;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        try {
            GitHubUserDTO userDTO = objectMapper.readValue(response.getBody() , GitHubUserDTO.class);

            GitHubUser user = new GitHubUser(username , userDTO.getName() , userDTO.getPublic_repos() , userDTO.getFollowers() , userDTO.getFollowing());
            return gitHubUserRepository.save(user);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("user with " +username + " not found");
        }
    }

    public GitHubUser getGitHubUserFromDatabase(String username) {
        return gitHubUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("GitHub User not found in database"));
    }
}
