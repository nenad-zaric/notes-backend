package com.anunnakisoftware.notes.registration.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository conformationTokenRepository) {
        this.confirmationTokenRepository = conformationTokenRepository;
    }
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public void deleteTokensByUserId(Long id) {
        confirmationTokenRepository.deleteByUserId(id);
    }
}
