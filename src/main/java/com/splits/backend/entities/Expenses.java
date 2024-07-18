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
@Table(name = "expenses")
public class Expenses {
    @Id
    @UuidGenerator
    private String expensesId;

    @OneToOne
    private Group group;

    // this expenses table will have a list of transactions.
    // now have to create dynamic columns for each member of the group
    @Convert(converter = ExpensesMapConverter.class)
    @Column(columnDefinition = "text")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<String, List<Double>> expensesMap;
}

