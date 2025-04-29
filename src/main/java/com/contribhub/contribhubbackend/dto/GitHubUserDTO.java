package com.contribhub.contribhubbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserDTO {

    private String name;
    private int public_repos;
    private int followers;
    private int following;
}
