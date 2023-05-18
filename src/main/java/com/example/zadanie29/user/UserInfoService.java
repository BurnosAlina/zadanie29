package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRole;
import com.example.zadanie29.userRole.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;

    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;

    private UserInfoMapper userInfoMapper = new UserInfoMapper();

    public UserInfoService(UserInfoRepository userInfoRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserInfoDto> findAll() {
        Iterable<UserInfo> allUsers = userInfoRepository.findAll();
        List<UserInfoDto> allUsersDto = new ArrayList<>();
        for (UserInfo userInfo : allUsers) {
            allUsersDto.add(userInfoMapper.convertToDto(userInfo));
        }
        return allUsersDto;
    }

    public UserInfoDto findByEmail(String email) {
        Optional<UserInfo> userOptional = userInfoRepository.findByEmail(email);
        UserInfoDto dto = new UserInfoDto();
        if (userOptional.isPresent()) {
            dto = userInfoMapper.convertToDto(userOptional.get());
        }
        return dto;
    }

    @Transactional
    public void register(UserRegistrationDto dto) {
        UserInfo user = new UserInfo();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        user.setPassword(passwordHash);

        Optional<UserRole> role = userRoleRepository.findByName("ROLE_User");
        if (role.isPresent()) {
            user.setRoles(new HashSet<>(Collections.singletonList(role.get())));
        } else {
            throw new NoSuchElementException("Nie ma takiej roli");
        }

        userInfoRepository.save(user);
    }

    @Transactional
    public UserInfoDto save(UserInfoDto userInfoDto) {
        Optional<UserInfo> userOptional = userInfoRepository.findByEmail(userInfoDto.getEmail());
        UserInfo user = new UserInfo();
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setFirstName(userInfoDto.getFirstName());
            user.setLastName(userInfoDto.getLastName());
            user.setRoles(userInfoDto.getRoles());
            if (userInfoDto.getNewPassword() != null && !userInfoDto.getNewPassword().isEmpty()) {
                String passwordHash = passwordEncoder.encode(userInfoDto.getNewPassword());
                user.setPassword(passwordHash);
            }
        }
        userInfoRepository.save(user);
        return userInfoMapper.convertToDto(user);
    }

    public UserInfoDto findById(Long id) {
        Optional<UserInfo> userOptional = userInfoRepository.findById(id);
        if (userOptional.isPresent()) {
            return userInfoMapper.convertToDto(userOptional.get());
        } else {
            throw new NoSuchElementException("User not found");
        }
    }
}
