package com.splits.backend.controller;

import com.splits.backend.dtos.ExpensesDto;
import com.splits.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    private final GroupService groupService;
    @Autowired
    public ExpenseController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{groupId}/expenses")
    public ResponseEntity<ExpensesDto> getExpenses(@PathVariable String groupId){
        return ResponseEntity.ok(groupService.getExpense(groupId));
    }
}
