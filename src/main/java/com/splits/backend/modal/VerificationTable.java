package com.splits.backend.modal;

import com.splits.backend.modal.enums.VerificationType;
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

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "userId")
    Users sender;

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "userId")
    Users receiver;

    @ManyToOne
    @JoinColumn(name = "groupId")
    GroupsTable groupsTable;

    VerificationType verificationType;

    long createdAt;

    long expiresAt;
}
