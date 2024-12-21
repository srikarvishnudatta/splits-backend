package com.splits.backend.Repository;

import com.splits.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(Long id);

    Optional<User> findUserByUsername(String username);
}
