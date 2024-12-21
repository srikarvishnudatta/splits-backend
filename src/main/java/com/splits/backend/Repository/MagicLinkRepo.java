package com.splits.backend.Repository;

import com.splits.backend.entities.MagicLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagicLinkRepo extends JpaRepository<MagicLink, Long> {
    Optional<MagicLink> findByToken(String token);
}
