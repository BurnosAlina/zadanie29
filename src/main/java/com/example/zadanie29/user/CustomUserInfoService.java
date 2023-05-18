package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserInfoService implements UserDetailsService {

    private UserInfoService userInfoService;

    public CustomUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoDto user = userInfoService.findByEmail(username);
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(UserInfoDto user) {
        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map((UserRole role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
