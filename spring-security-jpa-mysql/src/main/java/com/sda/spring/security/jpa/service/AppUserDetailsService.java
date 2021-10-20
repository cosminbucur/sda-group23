package com.sda.spring.security.jpa.service;

import com.sda.spring.security.jpa.model.AppUser;
import com.sda.spring.security.jpa.model.AppUserPrincipal;
import com.sda.spring.security.jpa.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find user by username
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("username not found " + username);
        }

        // transform user to userDetails
        return new AppUserPrincipal(appUser);
    }
}
