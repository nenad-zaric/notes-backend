package com.anunnakisoftware.notes.registration.token;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "created", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expires", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "confirmed")
    private LocalDateTime confirmedAt;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,  Long userId) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userId = userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
