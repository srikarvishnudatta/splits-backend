package com.splits.backend.repository;

import com.splits.backend.modal.GroupInvitationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInvitationTableRepo extends JpaRepository<GroupInvitationTable, Long> {
}
