package com.contribhub.contribhubbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stackoverflow_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackOverflowUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String stackOverflowUserId;
    private int reputation;

    public StackOverflowUser(String stackOverflowUserId, int reputation) {
        this.stackOverflowUserId = stackOverflowUserId;
        this.reputation = reputation;
    }
}
