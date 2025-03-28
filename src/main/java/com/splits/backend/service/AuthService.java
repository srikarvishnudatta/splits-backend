package com.splits.backend.service;

import com.splits.backend.dto.LoginDto;
import com.splits.backend.dto.NewUserDto;
import com.splits.backend.modal.Users;
import com.splits.backend.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;

    private final UsersRepo repo;
    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public AuthService(UsersRepo repo) {
        this.repo = repo;
    }

    public String verifyLogin(LoginDto dto){
        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            return jwtService.generateToken(dto.getEmail());
        }catch (AuthenticationException e){
            return "Bad Credentials";
        }
    }
    public void createUser(NewUserDto dto){
        var newUser = Users.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        repo.save(newUser);
    }
}
