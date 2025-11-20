package org.example.capstone_phase2.service;


import lombok.RequiredArgsConstructor;
import org.example.capstone_phase2.Model.Role;
import org.example.capstone_phase2.Model.User;
import org.example.capstone_phase2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String email, String rawPassword) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.ROLE_USER)
                .build();
        return userRepository.save(user);
    }
}
