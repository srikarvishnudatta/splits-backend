package com.splits.backend.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long verificationId;

    @Column(nullable = false)
    String email;

    long createdAt;

    long expiresAt;
}
