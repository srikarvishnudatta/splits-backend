package com.splits.backend.modal;

import com.splits.backend.modal.enums.Status;
import com.splits.backend.modal.enums.VerificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupInvitationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long groupInvitationId;

    Status status;

    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    Users sender;

    @ManyToOne
    @JoinColumn(name = "receiverId", nullable = false)
    Users receiver;


    @ManyToOne
    @JoinColumn(name = "groupId")
    GroupsTable groupsTable;

    VerificationType verificationType;

    long createdAt;

    long expiresAt;

    @PrePersist
    void createOn(){
        createdAt = new Date().getTime();
    }
}
