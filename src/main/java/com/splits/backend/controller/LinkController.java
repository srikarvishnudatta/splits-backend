package com.splits.backend.controller;


import com.splits.backend.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    private final GroupService groupService;

    public LinkController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("verify/{sender}/{token}/{receiver}/group={groupId}")
    public void verifyLink(@PathVariable String sender, @PathVariable String token, @PathVariable String receiver, @PathVariable String groupId){
        groupService.addMemberToGroup(sender, token, receiver, groupId);
    }
}
