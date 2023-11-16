package com.nisum.backendtest.service;

import com.nisum.backendtest.dto.UserRequestDto;
import com.nisum.backendtest.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto saveOrUpdateUser(String id, UserRequestDto userDto);
}
