package com.splits.backend.controller;

import com.splits.backend.dto.NewGroupDto;
import com.splits.backend.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String getAllGroups(){
        groupService.allGroups();
        return "Hello groups " ;
    }
    @GetMapping("/{groupId}")
    public void getGroupById(long id){

    }
    @PostMapping("/new")
    public void createGroup(@RequestBody NewGroupDto dto){
        groupService.createNewGroup(dto);
    }

    @PatchMapping("/update")
    public void updateGroup(){}
    @DeleteMapping("/delete")
    public void deleteGroup(){}

}
