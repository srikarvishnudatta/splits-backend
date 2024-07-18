package com.splits.backend.Repository;

import com.splits.backend.entities.Expenses;
import com.splits.backend.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expenses, String> {
    Optional<Expenses> findExpensesByGroup(Group group);
}
