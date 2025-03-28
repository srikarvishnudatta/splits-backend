package com.splits.backend.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long groupId;

    @Column(nullable = false)
            @Size(max = 10)
    String groupName;

    @OneToMany
    List<Users> members;

    long createdAt = new Date().getTime();

    @OneToMany(mappedBy = "groupMembership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMembershipTable> groupMemberships = new ArrayList<>();

    @OneToMany(mappedBy = "groupsTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VerificationTable> verificationTable = new ArrayList<>();

}
