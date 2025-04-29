package com.contribhub.contribhubbackend.service;

import com.contribhub.contribhubbackend.dto.StackOverflowResponseDTO;
import com.contribhub.contribhubbackend.dto.StackOverflowUserDTO;
import com.contribhub.contribhubbackend.model.StackOverflowUser;
import com.contribhub.contribhubbackend.repository.StackOverflowUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StackOverflowService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    StackOverflowUserRepository stackOverflowUserRepository;

    @Autowired
    ObjectMapper objectMapper;

    public StackOverflowUser fetchStackOverflowUserData(String stackOverflowUserId) {

        String apiUrl = "https://api.stackexchange.com/2.3/users/" + stackOverflowUserId + "?site=stackoverflow";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl , String.class);

        try {
            StackOverflowResponseDTO userResponseDTO = objectMapper.readValue(response.getBody() , StackOverflowResponseDTO.class);
            StackOverflowUserDTO userDTO = userResponseDTO.getItems().getFirst();

            StackOverflowUser user = new StackOverflowUser(stackOverflowUserId , userDTO.getReputation());
            return stackOverflowUserRepository.save(user);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("User with " +stackOverflowUserId +" not found");
        }
    }

    public StackOverflowUser getStackOverflowUserFromDatabase(String stackOverflowUserId) {
        return stackOverflowUserRepository.findByStackOverflowUserId(stackOverflowUserId)
                .orElseThrow(() -> new RuntimeException("StackOverflow User not found in database."));
    }
}
