package com.splits.backend.Repository;

import com.splits.backend.entities.Group;
import com.splits.backend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, String> {
    List<Transaction> findTransactionsByGroup(Group group);

    Optional<Transaction> findTransactionByTransactionId(String transactionId);

}
