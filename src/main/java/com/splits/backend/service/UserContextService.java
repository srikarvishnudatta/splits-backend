package com.splits.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {
    public String getUserName(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
