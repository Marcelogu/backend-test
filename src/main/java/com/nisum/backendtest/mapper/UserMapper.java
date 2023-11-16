package com.nisum.backendtest.mapper;

import com.nisum.backendtest.dto.UserRequestDto;
import com.nisum.backendtest.dto.UserResponseDto;
import com.nisum.backendtest.model.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRequestDto.getPassword()))")
    @Mapping(target = "phones", source = "phones")
    User userRequestToUser(UserRequestDto userRequestDto, @Context PasswordEncoder passwordEncoder);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "modified", source= "modified")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "token", source = "token")
    @Mapping(target = "isActive", expression = "java(true)")
    UserResponseDto userToUserResponseDto(User user);

    @Mapping(target = "modified", expression = "java(new java.util.Date())")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRequestDto.getPassword()))")
    @Mapping(target = "lastLogin", expression = "java(user.getLastLogin())")
    void updateUserFromRequest(UserRequestDto userRequestDto, @MappingTarget User user, @Context PasswordEncoder passwordEncoder);
}
