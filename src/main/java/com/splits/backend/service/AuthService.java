package com.splits.backend.service;

import com.splits.backend.Repository.UserRepo;
import com.splits.backend.dtos.ResponseJwtDto;
import com.splits.backend.dtos.UserDto;
import com.splits.backend.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    public String login(UserDto user){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if(auth.isAuthenticated()){
            var new_user = this.userRepo.findByUsername(user.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("cant find user"));
            return this.jwtService.generateToken(new_user);
        }
        return "wrong credentials";
    }

    public boolean createUser(UserDto user){
        var newPassword = encoder.encode(user.getPassword());
        var finalUser = User.builder().username(user.getUsername()).password(newPassword).build();
        System.out.println(finalUser);
        try {
            this.userRepo.save(finalUser);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
