package com.anunnakisoftware.notes.registration;

import com.anunnakisoftware.notes.registration.token.ConfirmationToken;
import com.anunnakisoftware.notes.registration.token.ConfirmationTokenService;
import com.anunnakisoftware.notes.security.PasswordEncoder;
import com.anunnakisoftware.notes.user.User;
import com.anunnakisoftware.notes.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public RegistrationService(EmailValidator emailValidator, UserService userService, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.emailValidator = emailValidator;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String register(RegistrationRequest request) {
        boolean validation = emailValidator.validate(request.getEmail());
        LocalDate currentDate = LocalDate.now();

        if(!validation){
            throw new IllegalStateException("Email not valid!");
        }

        return userService.addNewUser(
                new User(request.getUsername(),
                        request.getEmail(),
                        request.getName(),
                        request.getLastName(),
                        passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword()),
                        dateTimeFormatter.format(currentDate),
                        dateTimeFormatter.format(currentDate)

        ));
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUserId());
        return "confirmed";
    }



}
