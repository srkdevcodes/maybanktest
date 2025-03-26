package com.example.maybank.service;

import com.example.maybank.dto.LoginDto;
import com.example.maybank.entity.UserInfo;

public interface AuthService {

    String addUser(UserInfo userInfo);
}
