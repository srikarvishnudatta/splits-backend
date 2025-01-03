package com.splits.backend.service;

import com.splits.backend.Repository.UserRepo;
import com.splits.backend.dtos.UserDto;
import com.splits.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    private final UserRepo userRepo;

    @Autowired
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean loginUser(UserDto body){
        Optional<User> user = this.userRepo.findUserByUsername(body.getUsername());
        if (user.isEmpty()) throw new RuntimeException("user doesnt exist");
        return body.getPassword().equals(user.get().getPassword());
    }
    public boolean createUser(UserDto body){
        var finalUser = User.builder()
                .username(body.getUsername())
                .password(body.getPassword())
                .groupList(new ArrayList<>())
                .build();
        try{
            this.userRepo.save(finalUser);
            return true;
        }catch (Exception e){
            return false;
        }
    }
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
}
