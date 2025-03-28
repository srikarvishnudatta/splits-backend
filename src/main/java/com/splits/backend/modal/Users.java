package com.splits.backend.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long userId;

    @Column(nullable = false)
    @Size(max = 25)
    String firstName;

    @Column(nullable = false)
    @Size(max = 25)
    String lastName;

    @Column(nullable = false, unique = true)
    @Size(max = 40)
    String email;

    @Column(nullable = false)
    @Size(min = 8, max = 15)
    String password;

    boolean verified = false;

    @OneToMany(mappedBy = "userMembership", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GroupMembershipTable> groupMemberships = new ArrayList<>();

}
