package com.splits.backend.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// this path should not be secured for jwt
@RestController
@RequestMapping("verify")
public class TokenController {
    @PostMapping("/{token}/")
    public String verifyUser(@RequestParam String token){
        return token;
    }
}
