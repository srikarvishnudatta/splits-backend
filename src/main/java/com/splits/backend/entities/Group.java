package com.splits.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


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

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "userId")
    User groupOwner;

    private String members;

    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Expenses expenses;
}
