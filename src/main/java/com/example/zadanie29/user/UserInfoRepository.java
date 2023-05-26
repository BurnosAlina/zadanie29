package com.example.zadanie29.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);
}
