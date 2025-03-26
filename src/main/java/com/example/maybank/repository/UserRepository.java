package com.example.maybank.repository;

import com.example.maybank.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByEmail(String email);
}
