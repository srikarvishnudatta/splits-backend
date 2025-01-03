package com.splits.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "groupOwner", fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Group> groupList = new ArrayList<>();

    @ManyToMany(mappedBy = "groupMembers")
    private List<Group> memberGroups;
}
