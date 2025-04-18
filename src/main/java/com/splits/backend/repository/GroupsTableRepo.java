package com.splits.backend.repository;

import com.splits.backend.modal.GroupsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsTableRepo extends JpaRepository<GroupsTable, Long> {
    @Query("""
            SELECT DISTINCT g FROM GroupsTable \
            g INNER JOIN GroupMembershipTable gm \
            ON g.groupId=gm.groupMembership.groupId WHERE gm.userMembership.userId= :userId""")
    List<GroupsTable> findGroupsByUser(@Param("userId") Long userId);
}
