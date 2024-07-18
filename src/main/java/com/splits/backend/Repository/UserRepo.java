package com.splits.backend.Repository;

import com.splits.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUserId(String id);

    Optional<User> findUserByEmail(String email);
}
