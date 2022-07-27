package com.anunnakisoftware.notes.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Query(value = "SELECT * FROM tokens WHERE token = ?1", nativeQuery = true)
    Optional<ConfirmationToken> findByToken(String token);

    @Modifying
    @Query(value = "UPDATE tokens SET confirmed = ?2 WHERE token = ?1", nativeQuery = true)
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

    @Modifying
    @Query(value = "DELETE FROM tokens WHERE user_id = ?1", nativeQuery = true)
    void deleteByUserId(Long id);
}
