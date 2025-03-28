package com.splits.backend.repository;

import com.splits.backend.modal.VerificationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTableRepo extends JpaRepository<VerificationTable, Long> {

}
