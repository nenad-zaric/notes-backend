package com.anunnakisoftware.notes.delete;

import com.anunnakisoftware.notes.security.PasswordEncoder;
import com.anunnakisoftware.notes.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    private final UserService userService;
    private final PasswordEncoder encoder;

    public DeleteService(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    public void deleteUser(DeleteRequest request){
        Long requestId = request.getId();
        String requestPassword = request.getPassword();
        String userPassword = userService.getUserById(requestId).get().getPassword();

        boolean isMatching = encoder.bCryptPasswordEncoder().matches(requestPassword,userPassword);

        if(!isMatching){
            throw new IllegalStateException("Incorrect password");
        }
        userService.deleteUser(requestId);
    }
}
