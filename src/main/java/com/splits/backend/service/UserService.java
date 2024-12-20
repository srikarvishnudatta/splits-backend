package com.splits.backend.service;

import com.splits.backend.Repository.GroupRepo;
import com.splits.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;

    @Autowired
    public UserService(UserRepo userRepo, GroupRepo groupRepo) {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
    }

//    public ResponseDto createUserOrLoginUser(String email){
//        var result = userRepo.findUserByEmail(email).orElse(null);
//        if (result == null){
//            var user = User.builder().email(email).build();
//            userRepo.save(user);
//            return new ResponseDto(user.getUserId());
//        }
//        return new ResponseDto(result.getUserId());
//    }
//    public User findUserById(String id){
//        return userRepo.findUserByUserId(id).orElse(null);
//    }
//
//    public String createNewGroup(RequestGroupDto group, User owner){
//        var newgroup = Group.builder().groupOwner(owner).name(group.getName())
//                .createdAt(group.getCreatedAt())
//                .members(String.join(" ", group.getGroupMembers()))
//                .transactions(new ArrayList<>())
//                .build();
//        var result = groupRepo.findGroupsByGroupOwner(owner);
//        result.add(newgroup);
//        owner.setGroupList(result);
//        var expenseMap = createExpensesTable(newgroup.getMembers().split(" "));
//        newgroup.setExpensesMap(expenseMap);
//        groupRepo.save(newgroup);
//        userRepo.save(owner);
//        return newgroup.getGroupId();
//    }
//    private HashMap<String, Map<String, Double>> createExpensesTable(String[] members){
//        var map = new HashMap<String, Map<String, Double>>();
//        for (String member: members){
//            var innerMap = new HashMap<String, Double>();
//            for (String inner: members){
//                innerMap.put(inner, 0.0);
//            }
//            map.put(member, innerMap);
//        }
//        return map;
//    }
//    public void deleteGroup(String groupId, String userId){
//        var user = userRepo.findUserByUserId(userId).orElse(null);
//        var group = groupRepo.findGroupByGroupId(groupId).orElse(null);
//        if (user!=null && group !=null) {
//            var list = user.getGroupList();
//            list.remove(group);
//            user.setGroupList(list);
//            userRepo.save(user);
//        }
//    }
}
