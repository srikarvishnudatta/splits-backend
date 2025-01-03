package com.splits.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "groups")
public class Group {
    @Id
    @UuidGenerator
    private String groupId;

    private String name;
    private String createdAt;

    @JsonBackReference
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "userId")
    User groupOwner;


    @ManyToMany
    @JoinTable(
        name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> groupMembers;

    @JsonManagedReference
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    List<Transaction> transactions = new ArrayList<>();

}
