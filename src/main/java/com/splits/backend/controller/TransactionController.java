package com.splits.backend.controller;

import com.splits.backend.dtos.DeleteTransactionDto;
import com.splits.backend.dtos.TransactionRequestDto;
import com.splits.backend.dtos.TransactionResponseDto;
import com.splits.backend.entities.Transaction;
import com.splits.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final GroupService groupService;
    @Autowired
    public TransactionController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{groupId}/getTransactions")
    public ResponseEntity<TransactionResponseDto> getAllTransactions(@PathVariable String groupId){
        var transactions = groupService.getTransactionsByGroupId(groupId);
        var response = TransactionResponseDto.builder()
                .transactions(transactions)
                .expensesMap(groupService.getExpenseMap(groupId))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/trans/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId){
        return groupService.getTransaction(transactionId);
    }
    @PostMapping("/{groupId}/newTransaction")
    public void addNewTransaction(@PathVariable String groupId, @RequestBody TransactionRequestDto reqBody){
        groupService.addNewTransaction(groupId, reqBody);
    }
    @DeleteMapping("/{transactionId}/delete")
    public String deleteTransaction(@PathVariable String transactionId, @RequestBody DeleteTransactionDto body){
        return groupService.deleteTransaction(transactionId, body.getGroupId());
    }
}
