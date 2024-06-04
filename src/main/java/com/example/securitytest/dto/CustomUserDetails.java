package com.example.securitytest.dto;

import com.example.securitytest.entity.UserEntitiy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final UserEntitiy userEntitiy;
    public CustomUserDetails(UserEntitiy userEntitiy){

        this.userEntitiy = userEntitiy;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 사용자의 권한 리턴

        Collection<GrantedAuthority>collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return userEntitiy.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntitiy.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntitiy.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
