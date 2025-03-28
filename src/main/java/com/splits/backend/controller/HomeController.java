package com.splits.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/profile")
    public void getProfile(){

    }
    @GetMapping("/home")
    public void getHome(){

    }
}
