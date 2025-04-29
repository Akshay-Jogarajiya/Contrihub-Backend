package com.contribhub.contribhubbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "github_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String name;
    private int publicRepos;
    private int followers;
    private int following;

    public GitHubUser(String username, String name, int publicRepos, int followers, int following) {
        this.username = username;
        this.name = name;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
    }
}
