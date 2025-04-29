package com.contribhub.contribhubbackend.repository;

import com.contribhub.contribhubbackend.model.GitHubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitHubUserRepository extends JpaRepository<GitHubUser, Long> {

    Optional<GitHubUser> findByUsername(String username);
}
