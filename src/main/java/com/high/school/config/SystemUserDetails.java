package com.high.school.config;

import com.high.school.users.model.User;
import com.high.school.users.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserDetails implements UserDetailsService {
    @Autowired
        UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user=userRepo.findByUsername(username);
            if (user==null)
                throw new UsernameNotFoundException("Ooops No User");
            return new UserDetailsImpl(user);
        }
    }
