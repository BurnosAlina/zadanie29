package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRole;
import com.example.zadanie29.userRole.UserRoleDto;

import java.util.Set;

public class UserInfoDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<UserRoleDto> roles;
    private String newPassword;

    public UserInfoDto(Long id, String firstName, String lastName, String email, String password, Set<UserRoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleDto> roles) {
        this.roles = roles;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
