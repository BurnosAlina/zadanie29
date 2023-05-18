package com.example.zadanie29.userRole;

import com.example.zadanie29.user.UserInfo;

import java.util.Set;

public class UserRoleDto {

    private Long id;
    private String name;
    private String description;
    private Set<UserInfo> userInfo;

    public UserRoleDto(Long id, String name, String description, Set<UserInfo> userInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userInfo = userInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
