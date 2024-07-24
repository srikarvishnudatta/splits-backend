package com.splits.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Map;


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

    @OneToMany(mappedBy = "group")
    private List<Transaction> transactions;

    @Convert(converter = ExpensesMapConverter.class)
    @Column(columnDefinition = "text")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<String, List<Double>> expensesMap;
}
