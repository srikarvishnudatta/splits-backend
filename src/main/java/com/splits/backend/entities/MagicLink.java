package com.splits.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MagicLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String sender;

    private String receiver;

    private String token;

    private Date expiresAt;
}
