package com.splits.backend.repository;

import com.splits.backend.modal.GroupMembershipTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsMembershipTableRepo extends JpaRepository<GroupMembershipTable, Long> {
}
