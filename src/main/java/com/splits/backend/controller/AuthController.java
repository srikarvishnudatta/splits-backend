package com.splits.backend.controller;

import com.splits.backend.dtos.ResponseLoginDto;
import com.splits.backend.dtos.UserDto;
import com.splits.backend.service.AuthService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseLoginDto> login(@RequestBody UserDto body){
        try {
            var result = this.authService.loginUser(body);
            var entity = new ResponseLoginDto(Long.toString(result));
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDto body){
        var result = this.authService.createUser(body);
        if (result) return ResponseEntity.status(201).body("ok created");
        return ResponseEntity.ok("ok");
    }
}
