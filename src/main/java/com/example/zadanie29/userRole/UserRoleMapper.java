package com.example.zadanie29.userRole;

public class UserRoleMapper {

    public UserRoleDto convertToDto(UserRole userRole) {
        return new UserRoleDto(userRole.getId(), userRole.getName(), userRole.getDescription(), userRole.getUserInfo());
    }
}
