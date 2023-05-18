package com.example.zadanie29.userRole;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
    private UserRoleMapper userRoleMapper = new UserRoleMapper();

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDto> findAll() {
        Iterable<UserRole> allUserRoles = userRoleRepository.findAll();
        List<UserRoleDto> userRolesDto = new ArrayList<>();
        for (UserRole userRole : allUserRoles) {
            if (!isAlreadyOnList(userRole, userRolesDto)) {
                userRolesDto.add(userRoleMapper.convertToDto(userRole));
            }
        }
        return userRolesDto;
    }

    private boolean isAlreadyOnList(UserRole userRole, List<UserRoleDto> userRolesDto) {
        boolean isAlreadyOnList = false;
        for (UserRoleDto userRoleDto : userRolesDto) {
            isAlreadyOnList = userRole.getName().equals(userRoleDto.getName());
        }
        return isAlreadyOnList;
    }
}
