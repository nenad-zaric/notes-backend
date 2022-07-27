package com.anunnakisoftware.notes.user;

import com.anunnakisoftware.notes.registration.token.ConfirmationToken;
import com.anunnakisoftware.notes.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public UserService(UserRepository userRepository, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }

    public String addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()){
            throw new IllegalStateException("User with that email already exists");
        }

        userRepository.save(user);
        String  token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user.getId());
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //
        return String.join("Registration successful! --- Confirmation token:", token);
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("User doesn't exist!");
        }
        confirmationTokenService.deleteTokensByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found!"));
    }

    public void enableUser(Long userId) {
        userRepository.enableUser(userId);
    }
}
