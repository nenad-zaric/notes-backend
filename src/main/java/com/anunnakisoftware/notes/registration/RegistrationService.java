package com.anunnakisoftware.notes.registration;

import com.anunnakisoftware.notes.security.PasswordEncoder;
import com.anunnakisoftware.notes.user.User;
import com.anunnakisoftware.notes.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public RegistrationService(EmailValidator emailValidator, UserService userService, PasswordEncoder passwordEncoder) {
        this.emailValidator = emailValidator;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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

}
