package com.splits.backend.controller;
import com.splits.backend.dtos.InviteDto;
import com.splits.backend.dtos.RequestGroupDto;
import com.splits.backend.dtos.GroupResponseDto;
import com.splits.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("/{userId}/groups")
    public ResponseEntity<List<GroupResponseDto>> getAllGroupsOfUser(@PathVariable long userId){
        var result = groupService.getAllGroupsByUserId(userId);
        if(result == null) return ResponseEntity.ok().body(List.of());
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/{userId}/newGroup")
    public ResponseEntity<String> createNewGroupByUserId(@PathVariable long userId, @RequestBody RequestGroupDto body){
        String result= groupService.createNewGroup(body, userId);
        if (result == null) return ResponseEntity.status(403).body("Forbidden");
        return ResponseEntity.status(201).body("group created " + result);
    }

    @PostMapping("/{groupId}/invite")
    public void invite(@PathVariable String groupId,@RequestBody InviteDto invite){
        this.groupService.sendInvite(invite, groupId);
    }
    @DeleteMapping("/groups/{groupId}")
    public void deleteGroupByGroupId(@PathVariable String groupId){
       groupService.deleteGroup(groupId);
    }

}
