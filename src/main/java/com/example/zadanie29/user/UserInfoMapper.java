package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    UserRoleRepository userRoleRepository;

    public UserInfoMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserInfoDto convertToDto(UserInfo userInfo) {
        return new UserInfoDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getEmail(), userInfo.getPassword(), userInfo.getRole().getName());
    }
}
