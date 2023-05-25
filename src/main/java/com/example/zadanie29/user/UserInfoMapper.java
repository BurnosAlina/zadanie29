package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRole;
import com.example.zadanie29.userRole.UserRoleDto;
import com.example.zadanie29.userRole.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserInfoMapper {

    UserRoleRepository userRoleRepository;

    public UserInfoMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserInfoDto convertToDto(UserInfo userInfo) {
        return new UserInfoDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getEmail(), userInfo.getPassword(), convertRolesToDto(userInfo.getRoles()));
    }

    private Set<UserRoleDto> convertRolesToDto(Set<UserRole> roles) {
        Set<UserRoleDto> userRolesDto = new HashSet<>();
        for (UserRole role : roles) {
            UserRoleDto dtoRole = new UserRoleDto(role.getId(), role.getName(), role.getDescription());
            userRolesDto.add(dtoRole);
        }
        return userRolesDto;
    }

    public Set<UserRole> convertRolesFromDto(Set<UserRoleDto> rolesDto) {
        Set<UserRole> userRoles = new HashSet<>();
        for (UserRoleDto userRoleDto : rolesDto) {
            UserRole userRole = userRoleRepository.findById(userRoleDto.getId()).orElseThrow();
            userRoles.add(userRole);
        }
        return userRoles;
    }
}
