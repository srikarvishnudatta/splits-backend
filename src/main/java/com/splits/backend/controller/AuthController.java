package com.splits.backend.controller;

import com.splits.backend.dtos.ResponseJwtDto;
import com.splits.backend.dtos.UserDto;
import com.splits.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseJwtDto> login(@RequestBody UserDto user){
        var response = this.service.login(user);
        var entity = new ResponseJwtDto(response);
        if (response.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDto user){
        var response = this.service.createUser(user);
        if (!response) return ResponseEntity.badRequest().body("user already exists");
        return new ResponseEntity<>("user created", HttpStatus.CREATED);
    }
}
