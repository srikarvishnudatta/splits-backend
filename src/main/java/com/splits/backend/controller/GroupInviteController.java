package com.splits.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups/invites")
public class GroupInviteController {
    @PostMapping("/send")
    public void sendInvites(){

    }
    @GetMapping("/")
    public void getInvites(){

    }
    @GetMapping("/{groupId}/accept")
    public void acceptInvite(){}

    @GetMapping("/{groupId}/decline")
    public void declineInvite(){}
}
