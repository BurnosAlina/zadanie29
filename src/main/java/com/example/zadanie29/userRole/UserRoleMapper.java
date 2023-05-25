package com.example.zadanie29.userRole;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserRoleMapper {

    public UserRoleDto convertToDto(UserRole userRole) {
        return new UserRoleDto(userRole.getId(), userRole.getName(), userRole.getDescription());
    }
}
