package com.splits.backend.Repository;

import com.splits.backend.entities.Group;
import com.splits.backend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, String> {
    List<Transaction> findTransactionsByGroup(Group group);

}
