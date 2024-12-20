package com.splits.backend.service;

import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

    private final GroupRepo groupRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo, TransactionRepo transactionRepo ) {
        this.groupRepo = groupRepo;
        this.transactionRepo = transactionRepo;
    }

//    public List<GroupResponseDto> getAllGroupsByUser(User owner){
//       var result = groupRepo.findGroupsByGroupOwner(owner);
//       return result.stream().map(this::convertDto).collect(Collectors.toList());
//    }
//    private GroupResponseDto convertDto(Group group){
//        return GroupResponseDto.builder().groupId(group.getGroupId())
//                .name(group.getName())
//                .groupMembers(group.getMembers().split(" "))
//                .build();
//    }
//    public String deleteGroup(String groupId){
//        var result = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("cannot"));
//        groupRepo.delete(result);
//        return result.getGroupOwner().getUserId();
//    }
//    public List<Transaction> getTransactionsByGroupId(String groupId){
//        var groupFound = groupRepo.findGroupByGroupId(groupId).orElseThrow(()-> new RuntimeException("Cannot find group"));
//        return transactionRepo.findTransactionsByGroup(groupFound);
//    }
//    public Map<String, Map<String, Double>> getExpenseMap(String groupId){
//        return new ExpensesMapConverter().convertToEntityAttribute(groupRepo.findExpensesMapByGroupId(groupId));
//    }
//    public void addNewTransaction(String groupId, TransactionRequestDto reqBody){
//        var groupFound = groupRepo.findGroupByGroupId(groupId).orElseThrow(() -> new RuntimeException("Cannot find group"));
//        var transactions = groupFound.getTransactions();
//        var newTransaction = Transaction.builder().transactionName(reqBody.getTransactionName())
//                .paidBy(reqBody.getPaidBy())
//                .transactionValue(reqBody.getTransactionValue())
//                .build();
//        var expenseMap = groupFound.getExpensesMap();
//        expenseMap = updateExpenseMap(expenseMap, reqBody.getPaidBy(), reqBody.getSplitAmong());
//        transactions.add(newTransaction);
//        groupFound.setExpensesMap(expenseMap);
//        groupFound.setTransactions(transactions);
//        newTransaction.setGroup(groupFound);
//        newTransaction.setSplitAmong(reqBody.getSplitAmong());
//        transactionRepo.save(newTransaction);
//        groupRepo.save(groupFound);
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
