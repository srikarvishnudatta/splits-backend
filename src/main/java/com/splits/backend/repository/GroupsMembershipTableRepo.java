package com.splits.backend.repository;

import com.splits.backend.modal.GroupMembershipTable;
import com.splits.backend.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsMembershipTableRepo extends JpaRepository<GroupMembershipTable, Long> {
    List<GroupMembershipTable> findByUserMembership(Users user);
}
