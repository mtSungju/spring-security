package com.example.securitytest.service;

import com.example.securitytest.dto.CustomUserDetails;
import com.example.securitytest.entity.UserEntitiy;
import com.example.securitytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntitiy userData = userRepository.findByUsername(username);

        System.out.println("userData ====>" + userData);

        if(userData != null){
            System.out.println("come!!!");
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
