package com.splits.backend.repository;

import com.splits.backend.modal.GroupsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsTableRepo extends JpaRepository<GroupsTable, Long> {
}
