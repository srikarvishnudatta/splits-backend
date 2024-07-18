package com.splits.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
    @UuidGenerator
    private String userId;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "groupOwner")
            @ToString.Exclude
    List<Group> groupList = new ArrayList<>();
}
