package com.splits.backend.controller;

import com.splits.backend.dto.LoginDto;
import com.splits.backend.dto.NewUserDto;
import com.splits.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        var result =  authService.verifyLogin(loginDto);
        if (result.equals("Bad Credentials")) return ResponseEntity.status(401).body(result);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody NewUserDto newUserDto){
       try {
           authService.createUser(newUserDto);
           return ResponseEntity.status(201).body("Email verification link has been sent");
       }catch (Exception e){
           return ResponseEntity.status(409).body("User already exists");
       }
    }
    @GetMapping("/password/reset")
    public void resetPassword(){

    }
    @PatchMapping("/password/update")
    public void updatePassword(){

    }
    @GetMapping("/verify-account")
    public void verifyUser(@RequestParam String email, @RequestParam long expiresAt){
        authService.verifyUserService(email, expiresAt);
    }
    @GetMapping("/logout")
    public void logoutUser(){

    }
}
