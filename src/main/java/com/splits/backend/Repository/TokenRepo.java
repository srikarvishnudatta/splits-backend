package com.splits.backend.Repository;

import com.splits.backend.entities.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Tokens, Long> {
    Optional<Tokens> findByToken(String token);
}
