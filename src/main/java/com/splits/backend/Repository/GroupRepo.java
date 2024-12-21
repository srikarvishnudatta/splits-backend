package com.splits.backend.Repository;

import com.splits.backend.entities.Group;
import com.splits.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<Group, String> {

    @Query(value = "SELECT * from groups WHERE user_Id = :userId", nativeQuery = true)
    List<Group> findGroupsByUserId(@Param("userId") Long userId);
    List<Group> findGroupsByGroupOwner(User owner);
    Optional<Group> findGroupByGroupId(String groupId);
    @Query(value = "SELECT expenses_map FROM groups WHERE group_id = :groupId", nativeQuery = true)
    String findExpensesMapByGroupId(@Param("groupId") String groupId);

}
