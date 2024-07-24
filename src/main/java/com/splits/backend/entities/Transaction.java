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
@Table(name = "transactions")
public class Transaction {
    @Id
    @UuidGenerator
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "transactions")
    private Group group;

    private String transactionName;
    private Double transactionValue;
    private String paidBy;
    private String splitAmong;
}
