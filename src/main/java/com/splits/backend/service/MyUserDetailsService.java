package com.splits.backend.service;

import com.splits.backend.modal.UsersPrincipal;
import com.splits.backend.repository.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepo repo;

    public MyUserDetailsService(UsersRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repo.findByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UsersPrincipal(user.get());
    }
}
