package com.splits.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @GetMapping("/")
    public String getAllGroups(){
        return "Hello groups";
    }
    @GetMapping("/{groupId}")
    public void getGroupById(long id){

    }
    @PostMapping("/new")
    public void createGroup(){}

    @PatchMapping("/update")
    public void updateGroup(){}
    @DeleteMapping("/delete")
    public void deleteGroup(){}

}
