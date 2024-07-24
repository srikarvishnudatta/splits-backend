package com.splits.backend.service;

import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.Repository.TransactionRepo;
import com.splits.backend.dtos.GroupResponseDto;
import com.splits.backend.dtos.TransactionRequestDto;
import com.splits.backend.entities.ExpensesMapConverter;
import com.splits.backend.entities.Group;
import com.splits.backend.entities.Transaction;
import com.splits.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepo groupRepo;
    private final TransactionRepo transactionRepo;


    @Autowired
    public GroupService(GroupRepo groupRepo, TransactionRepo transactionRepo ) {
        this.groupRepo = groupRepo;
        this.transactionRepo = transactionRepo;
    }

    public List<GroupResponseDto> getAllGroupsByUser(User owner){
       var result = groupRepo.findGroupsByGroupOwner(owner);
       return result.stream().map(this::convertDto).collect(Collectors.toList());
    }
    private GroupResponseDto convertDto(Group group){
        return GroupResponseDto.builder().groupId(group.getGroupId())
                .date(group.getCreatedAt()).name(group.getName())
                .groupMembers(group.getMembers().split(" "))
                .build();
    }
    public String deleteGroup(String groupId){
        var result = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cannot"));
        groupRepo.delete(result);
        return result.getGroupOwner().getUserId();
    }
    public List<Transaction> getTransactionsByGroupId(String groupId){
        var groupFound = groupRepo.findById(groupId).orElseThrow(()-> new RuntimeException("Cannot find group"));
        return transactionRepo.findTransactionsByGroup(groupFound);
    }
    public Map<String, List<Double>> getExpenseMap(String groupId){
        return new ExpensesMapConverter().convertToEntityAttribute(groupRepo.findExpensesMapByGroupId(groupId));
    }
    public void addNewTransaction(String groupId, TransactionRequestDto reqBody){
        var group = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("Cannot find group"));
        var newTransaction = Transaction.builder()
                .transactionName(reqBody.getTransactionName())
                .transactionValue(reqBody.getTransactionValue())
                .paidBy(reqBody.getPaidBy())
                .splitAmong(reqBody.getSplitAmong())
                .group(group)
                .build();

        var transactions = group.getTransactions();
        if (transactions.isEmpty()) transactions = new ArrayList<>();
        transactions.add(newTransaction);
        group.setTransactions(transactions);
        group.setExpensesMap(reqBody.getExpensesMap());
        groupRepo.save(group);
        transactionRepo.save(newTransaction);
    }
}
