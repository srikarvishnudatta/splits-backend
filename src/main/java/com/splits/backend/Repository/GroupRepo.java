package com.splits.backend.Repository;

import com.splits.backend.entities.Group;
import com.splits.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<Group, String> {
    List<Group> findGroupsByGroupOwner(User owner);
    Optional<Group> findGroupByGroupId(String groupId);
}
