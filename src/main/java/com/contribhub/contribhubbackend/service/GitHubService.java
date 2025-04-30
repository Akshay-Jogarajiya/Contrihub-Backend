package com.contribhub.contribhubbackend.service;

import com.contribhub.contribhubbackend.dto.GitHubUserDTO;
import com.contribhub.contribhubbackend.model.GitHubUser;
import com.contribhub.contribhubbackend.repository.GitHubUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${github.token}")
    private String githubToken;

    @Autowired
    GitHubUserRepository gitHubUserRepository;

    @Autowired
    ObjectMapper objectMapper;

    public GitHubUser fetchGitHubUserData(String username) {
        String apiUrl = "https://api.github.com/users/" +username;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            GitHubUserDTO userDTO = objectMapper.readValue(response.getBody() , GitHubUserDTO.class);

            Optional<GitHubUser> optionalUser = gitHubUserRepository.findByUsername(username);
            GitHubUser user;

            if (optionalUser.isPresent()) {

                user = optionalUser.get();
                user.setUsername(username);
                user.setName(userDTO.getName());
                user.setPublicRepos(userDTO.getPublic_repos());
                user.setFollowers(userDTO.getFollowers());
                user.setFollowing(userDTO.getFollowing());
            } else {
                user = new GitHubUser(
                        username,
                        userDTO.getName(),
                        userDTO.getPublic_repos(),
                        userDTO.getFollowers(),
                        userDTO.getFollowing());
            }

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
