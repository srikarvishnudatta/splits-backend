package com.splits.backend.service;

import com.splits.backend.Repository.ExpenseRepo;
import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.dtos.ExpensesDto;
import com.splits.backend.dtos.GroupResponseDto;
import com.splits.backend.entities.Expenses;
import com.splits.backend.entities.Group;
import com.splits.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepo groupRepo;
    private final ExpenseRepo expenseRepo;
    @Autowired
    public GroupService(GroupRepo groupRepo, ExpenseRepo expenseRepo) {
        this.groupRepo = groupRepo;
        this.expenseRepo = expenseRepo;
    }

    public List<GroupResponseDto> getAllGroupsByUser(User owner){
       var result = groupRepo.findGroupsByGroupOwner(owner);
       return result.stream().map(this::convertDto).collect(Collectors.toList());
    }
    private GroupResponseDto convertDto(Group group){
        return GroupResponseDto.builder().groupId(group.getGroupId())
                .date(group.getCreatedAt()).name(group.getName())
                .members(group.getMembers().split(" "))
                .build();
    }
    public String deleteGroup(String groupId){
        var result = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cannot"));
        groupRepo.delete(result);
        return result.getGroupOwner().getUserId();
    }
    public ExpensesDto getExpense(String groupId){
        var group = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cant find group"));
        return convertExpensesDto(expenseRepo.findExpensesByGroup(group).orElseThrow(()-> new RuntimeException("cannot find expense")));
    }
    private ExpensesDto convertExpensesDto(Expenses expenses){
        return ExpensesDto.builder().expensesId(expenses.getExpensesId()).expensesMap(expenses.getExpensesMap()).build();
    }
}
