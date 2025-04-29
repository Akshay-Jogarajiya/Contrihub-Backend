package com.contribhub.contribhubbackend.repository;

import com.contribhub.contribhubbackend.model.StackOverflowUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StackOverflowUserRepository extends JpaRepository<StackOverflowUser , Long> {

    Optional<StackOverflowUser> findByStackOverflowUserId(String stackOverflowUserId);
}
