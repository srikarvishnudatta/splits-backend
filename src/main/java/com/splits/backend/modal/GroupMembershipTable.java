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
public class GroupMembershipTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
            @JoinColumn(name = "userId",nullable = false)
    Users userMembership;

    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false)
    GroupsTable groupMembership;

    boolean isOwner = false;
}
