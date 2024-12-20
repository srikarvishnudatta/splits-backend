package com.splits.backend.controller;
import com.splits.backend.service.GroupService;
import com.splits.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    private final GroupService groupService;

    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }
    @GetMapping("/groups")
    public ResponseEntity<String> groups(Authentication authentication){
        var username = authentication.getName();
        if (username.isEmpty()) return ResponseEntity.badRequest().body("bad");
        return ResponseEntity.ok(username);
    }
    @PostMapping("/newGroup")
    public void createGroup(){
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // call group service to create a new group
    }

    @PostMapping("/addFriend")
    public void addFriend(){
        // cannot send email to an account that doesnt exist
        // generate a token, use mail service to send a link.
    }

//    @PostMapping("/user")
//    public ResponseEntity<ResponseDto> createOrLoginUser(@RequestBody String body){
//        var result = userService.createUserOrLoginUser(body);
//        return ResponseEntity.status(201).body(result);
//    }
//    @GetMapping("/{userId}/groups")
//    public ResponseEntity<List<GroupResponseDto>> getAllGroupsOfUser(@PathVariable String userId){
//        var result = userService.findUserById(userId);
//        if(result == null) return ResponseEntity.ok().body(List.of());
//        return ResponseEntity.ok().body(groupService.getAllGroupsByUser(result));
//    }
//
//    @PostMapping("/{userId}/newGroup")
//    public ResponseEntity<String> createNewGroupByUserId(@PathVariable String userId, @RequestBody RequestGroupDto body){
//        var owner = userService.findUserById(userId);
//        if (owner == null) return ResponseEntity.status(404).body("user not found");
//        String result= userService.createNewGroup(body, owner);
//        if (result == null) return ResponseEntity.status(403).body("Forbidden");
//        return ResponseEntity.status(201).body("group created " + result);
//    }
//    @DeleteMapping("/groups/{groupId}")
//    public void deleteGroupByGroupId(@PathVariable String groupId){
//       var userId = groupService.deleteGroup(groupId);
//       userService.deleteGroup(groupId, userId);
//    }
}
