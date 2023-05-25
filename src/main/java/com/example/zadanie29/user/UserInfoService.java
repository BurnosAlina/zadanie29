package com.example.zadanie29.user;

import com.example.zadanie29.userRole.UserRole;
import com.example.zadanie29.userRole.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoRepository userInfoRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, UserInfoMapper userInfoMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoMapper = userInfoMapper;
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
        UserInfo userInfo = userInfoRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        return userInfoMapper.convertToDto(userInfo);
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
        UserInfo userInfo = userInfoRepository.findByEmail(userInfoDto.getEmail())
                .orElseThrow(NoSuchElementException::new);
            userInfo.setFirstName(userInfoDto.getFirstName());
            userInfo.setLastName(userInfoDto.getLastName());
            userInfo.setRoles(userInfoMapper.convertRolesFromDto(userInfoDto.getRoles()));
            if (StringUtils.hasText(userInfoDto.getNewPassword())) {
                String passwordHash = passwordEncoder.encode(userInfoDto.getNewPassword());
                userInfo.setPassword(passwordHash);
            }
            userInfoRepository.save(userInfo);
        return userInfoMapper.convertToDto(userInfo);
    }

    public UserInfoDto findById(Long id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return userInfoMapper.convertToDto(userInfo);
    }
}
