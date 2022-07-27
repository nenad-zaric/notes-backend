package com.anunnakisoftware.notes.user;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            value = "SELECT * FROM USERS u WHERE u.email = ?1",
            nativeQuery = true)
    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT * FROM USERS u WHERE u.email = ?1",nativeQuery = true)
    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query(value ="UPDATE users SET enabled = TRUE WHERE id = ?1", nativeQuery = true)
    void enableUser(Long userId);
}
