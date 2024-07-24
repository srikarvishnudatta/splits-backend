package com.splits.backend.controller;

import com.splits.backend.dtos.TransactionRequestDto;
import com.splits.backend.dtos.TransactionResponseDto;
import com.splits.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final GroupService groupService;
    @Autowired
    public TransactionController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{groupId}/getTransactions")
    public TransactionResponseDto getAllTransactions(@PathVariable String groupId){
        return TransactionResponseDto.builder().transactions(groupService.getTransactionsByGroupId(groupId))
                .expensesMap(groupService.getExpenseMap(groupId))
                .build();
    }
    @PostMapping("/{groupId}/newTransaction")
    public void addNewTransaction(@PathVariable String groupId, @RequestBody TransactionRequestDto reqBody){
        groupService.addNewTransaction(groupId, reqBody);
    }
}
