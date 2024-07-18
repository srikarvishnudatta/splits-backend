package com.splits.backend.service;

import com.splits.backend.Repository.ExpenseRepo;
import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.Repository.UserRepo;
import com.splits.backend.dtos.RequestGroupDto;
import com.splits.backend.dtos.ResponseDto;
import com.splits.backend.entities.Expenses;
import com.splits.backend.entities.Group;
import com.splits.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;
    private final ExpenseRepo expenseRepo;

    @Autowired
    public UserService(UserRepo userRepo, GroupRepo groupRepo, ExpenseRepo expenseRepo) {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
        this.expenseRepo = expenseRepo;
    }

    public ResponseDto createUserOrLoginUser(String email){
        var result = userRepo.findUserByEmail(email).orElse(null);
        if (result == null){
            var user = User.builder().email(email).build();
            userRepo.save(user);
            return new ResponseDto(user.getUserId());
        }
        return new ResponseDto(result.getUserId());
    }
    public User findUserById(String id){
        return userRepo.findUserByUserId(id).orElse(null);
    }

    public String createNewGroup(RequestGroupDto group, User owner){
        var newgroup = Group.builder().groupOwner(owner).name(group.getName())
                .createdAt(group.getCreatedAt())
                .members(String.join(" ", group.getGroupMembers()))
                .build();
        System.out.println(newgroup);
        var result = groupRepo.findGroupsByGroupOwner(owner);
        if (result == null || result.isEmpty()){
            // create a new array list;
            result = new ArrayList<>();
        }
        result.add(newgroup);
        owner.setGroupList(result);
        var expense = createExpensesTable(newgroup.getMembers().split(" "), newgroup);

        groupRepo.save(newgroup);
        userRepo.save(owner);
        expenseRepo.save(expense);
        newgroup.setExpenses(expense);
        groupRepo.save(newgroup);
        return newgroup.getGroupId();
    }
    private Expenses createExpensesTable(String[] members, Group group){
        var map = new HashMap<String, List<Double>>();
        for (String member: members) {
            map.put(member, Collections.nCopies(members.length, 0.0));
        }
        return Expenses.builder().group(group).expensesMap(map).build();
    }
    public void deleteGroup(String groupId, String userId){
        var user = userRepo.findUserByUserId(userId).orElse(null);
        var group = groupRepo.findGroupByGroupId(groupId).orElse(null);
        if (user!=null && group !=null) {
            var list = user.getGroupList();
            list.remove(group);
            user.setGroupList(list);
            userRepo.save(user);
        }
    }
}
