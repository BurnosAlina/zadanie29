package com.example.zadanie29.user;

import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    public UserInfoDto convertToDto(UserInfo userInfo) {
        return new UserInfoDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getEmail(), userInfo.getPassword(), userInfo.getRoles());
    }
}
