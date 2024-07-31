package com.splits.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @UuidGenerator
    private String transactionId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "groupId")
    Group group;

    private String transactionName;
    private Double transactionValue;
    private String paidBy;

    @Convert(converter = SplitAmongConverter.class)
    private Map<String, Double> splitAmong;
}
