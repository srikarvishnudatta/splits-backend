package com.splits.backend.controller;
import com.splits.backend.dtos.RequestGroupDto;
import com.splits.backend.dtos.GroupResponseDto;
import com.splits.backend.dtos.ResponseDto;
import com.splits.backend.service.GroupService;
import com.splits.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    private final GroupService groupService;

    @Autowired
    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDto> createOrLoginUser(@RequestBody String body){
        var result = userService.createUserOrLoginUser(body);
        return ResponseEntity.status(201).body(result);
    }
    @GetMapping("/{userId}/groups")
    public ResponseEntity<List<GroupResponseDto>> getAllGroupsOfUser(@PathVariable String userId){
        var result = userService.findUserById(userId);
        if(result == null) return ResponseEntity.ok().body(List.of());
        return ResponseEntity.ok().body(groupService.getAllGroupsByUser(result));
    }

    @PostMapping("/{userId}/newGroup")
    public ResponseEntity<String> createNewGroupByUserId(@PathVariable String userId, @RequestBody RequestGroupDto body){
        var owner = userService.findUserById(userId);
        if (owner == null) return ResponseEntity.status(404).body("user not found");
        String result= userService.createNewGroup(body, owner);
        if (result == null) return ResponseEntity.status(403).body("Forbidden");
        return ResponseEntity.status(201).body("group created " + result);
    }
    @DeleteMapping("/groups/{groupId}")
    public void deleteGroupByGroupId(@PathVariable String groupId){
       var userId = groupService.deleteGroup(groupId);
       userService.deleteGroup(groupId, userId);
    }
}
