package com.splits.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;

    private String token;
    private String createdAt;

    private String author;
    private String receiver;
}
