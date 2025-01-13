package com.splits.backend.service;

import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.Repository.TransactionRepo;
import com.splits.backend.Repository.UserRepo;
import com.splits.backend.dtos.GroupResponseDto;
import com.splits.backend.dtos.InviteDto;
import com.splits.backend.dtos.RequestGroupDto;
import com.splits.backend.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GroupService {

    private final GroupRepo groupRepo;
    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    private final AddMemberService addMemberService;

    public GroupService(GroupRepo groupRepo,
                        TransactionRepo transactionRepo,
                        UserRepo userRepo,
                        AddMemberService addMemberService) {
        this.groupRepo = groupRepo;
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.addMemberService = addMemberService;
    }

    public List<GroupResponseDto> getAllGroupsByUserId(long id){
       var result = groupRepo.findGroupsByUserId(id);
       return result.stream().map(this::convertDto).collect(Collectors.toList());
    }

    private GroupResponseDto convertDto(Group group){
        return GroupResponseDto.builder().groupId(group.getGroupId())
                .name(group.getName())
                .createdAt(group.getCreatedAt())
                .groupMembers(List.of(group.getGroupMembers().toString().split(",")))
                .build();
    }
    public String createNewGroup(RequestGroupDto group, long id){
        var owner = userRepo.findUserByUserId(id)
                .orElseThrow( () -> new RuntimeException("user cant be found"));
        var newgroup = Group.builder()
                .groupOwner(owner)
                .name(group.getName())
                .createdAt(new Date().toString())
                .groupMembers(new ArrayList<>())
                .transactions(new ArrayList<>())
                .build();
        var result = groupRepo.findGroupsByGroupOwner(owner);

        result.add(newgroup);
        owner.getGroupList().add(newgroup);
        groupRepo.save(newgroup);
        userRepo.save(owner);
        return newgroup.getGroupId();
    }
    private InviteStatus[] inviteMembers(String[] members){
        // send invites for each member in the list!
        var responses = new InviteStatus[members.length];
        for (int i=0; i< members.length; i++){
           var user =  userRepo.findUserByUsername(members[i]).orElse(null);
           if (user != null) responses[i] = InviteStatus.PENDING;

           else responses[i] = InviteStatus.FAILED;
        }
        return responses;
        // return array of status for each email, success or not.
    }
    public long deleteGroup(String groupId){
        var result = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cannot"));
        groupRepo.delete(result);
        var userId = result.getGroupOwner().getUserId();
        var user = userRepo.findUserByUserId(userId).orElseThrow(() -> new RuntimeException("user doest exist"));
        var list = user.getGroupList();
        list.remove(result);
        user.setGroupList(list);
        userRepo.save(user);
        return userId;
    }
    public void sendInvite(InviteDto dto, String groupId){
        this.addMemberService.sendInvite(dto.getSender(), dto.getReceiver(), dto.getGroupName(), groupId);
    }

    public void addMemberToGroup(String sender, String token, String receiver, String groupId) {
        var group = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cant find group"));
        boolean truth = this.addMemberService.verifyInvite(token, sender, receiver);
        if (truth){
            var member = this.userRepo.findUserByUsername(receiver)
                    .orElseThrow(() -> new RuntimeException("cant validate sender"));
            group.getGroupMembers().add(member);
           groupRepo.save(group);
        }
    }
//    public List<Transaction> getTransactionsByGroupId(String groupId){
//        var groupFound = groupRepo.findGroupByGroupId(groupId).orElseThrow(()-> new RuntimeException("Cannot find group"));
//        return transactionRepo.findTransactionsByGroup(groupFound);
//    }
//    public Map<String, Map<String, Double>> getExpenseMap(String groupId){
//        return new ExpensesMapConverter().convertToEntityAttribute(groupRepo.findExpensesMapByGroupId(groupId));
//    }
//    public void addNewTransaction(String groupId, TransactionRequestDto reqBody){
////        var groupFound = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("Cannot find group"));
////        var transactions = groupFound.getTransactions();
////        var newTransaction = Transaction.builder().transactionName(reqBody.getTransactionName())
////                .paidBy(reqBody.getPaidBy())
////                .transactionValue(reqBody.getTransactionValue())
////                .build();
////       //  var expenseMap = groupFound.getExpensesMap();
////        expenseMap = updateExpenseMap(expenseMap, reqBody.getPaidBy(), reqBody.getSplitAmong());
////        transactions.add(newTransaction);
////        groupFound.setExpensesMap(expenseMap);
////        groupFound.setTransactions(transactions);
////        newTransaction.setGroup(groupFound);
////        newTransaction.setSplitAmong(reqBody.getSplitAmong());
////        transactionRepo.save(newTransaction);
////        groupRepo.save(groupFound);
//    }
//    public Transaction getTransaction(String transactionId){
//        return transactionRepo.findTransactionByTransactionId(transactionId).orElseThrow(() -> new RuntimeException("Cannot find transaction"));
//    }
//    public Map<String, Map<String, Double>> updateExpenseMap(Map<String, Map<String, Double>> expensesMap, String paidBy, Map<String, Double> splitAmong){
//        return mapHelper(true, expensesMap, paidBy, splitAmong);
//    }
//    // toggle true, update map, else delete from map
//    private Map<String, Map<String, Double>> mapHelper(boolean toggle, Map<String, Map<String, Double>> expensesMap, String paidBy, Map<String, Double> splitAmong){
//        var members = splitAmong.keySet();
//        for (var member: members){
//            var value = expensesMap.get(paidBy).get(member);
//            if(toggle) value += splitAmong.get(member);
//            else value -= splitAmong.get(member);
//            value = Math.round(value * 100.0) / 100.0;
//            expensesMap.get(paidBy).put(member, value);
//
//            value = expensesMap.get(member).get(paidBy);
//            if(toggle) value -= splitAmong.get(member);
//            else value += splitAmong.get(member);
//            value = Math.round(value * 100.0) / 100.0;
//            expensesMap.get(member).put(paidBy, value);
//        }
//        return expensesMap;
//    }
//
//    public String deleteTransaction(String transactionId, String groupId) {
//        var transactionFound = transactionRepo.findTransactionByTransactionId(transactionId).orElseThrow(() -> new RuntimeException("Cannot find transaction"));
//        transactionRepo.delete(transactionFound);
//        var group = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("Cannot find group"));
//        var expensesMap = group.getExpensesMap();
//        var paidBy = transactionFound.getPaidBy();
//        var splitAmong = transactionFound.getSplitAmong();
//        group.setExpensesMap(mapHelper(false, expensesMap, paidBy, splitAmong));
//        group.getTransactions().remove(transactionFound);
//        groupRepo.save(group);
//        return "deleted";
//    }
//
//    public void updateTransaction(String transactionId, TransactionRequestDto reqBody){
//        var transactionFound = transactionRepo.findTransactionByTransactionId(transactionId).orElseThrow(() -> new RuntimeException("Cannot find transaction"));
//        var groupFound = transactionFound.getGroup();
//        groupFound.getTransactions().remove(transactionFound);
//        var expensesMap = groupFound.getExpensesMap();
//        var paidBy = transactionFound.getPaidBy();
//        transactionFound.setPaidBy(paidBy);
//        transactionFound.setTransactionName(reqBody.getTransactionName());
//        transactionFound.setTransactionValue(reqBody.getTransactionValue());
//        // basically remove the old value from the transaction
//        var splitAmong = transactionFound.getSplitAmong();
//        mapHelper(false, expensesMap, paidBy, splitAmong);
//
//        // add the new value to the transaction
//        var newSplitAmong = reqBody.getSplitAmong();
//        mapHelper(true, expensesMap, reqBody.getPaidBy(), newSplitAmong);
//        transactionFound.setSplitAmong(newSplitAmong);
//        groupFound.setExpensesMap(expensesMap);
//        groupFound.getTransactions().add(transactionFound);
//        transactionRepo.save(transactionFound);
//        groupRepo.save(groupFound);
//    }
}
