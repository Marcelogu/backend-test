package com.nisum.backendtest.service.impl;

import com.nisum.backendtest.dto.UserRequestDto;
import com.nisum.backendtest.dto.UserResponseDto;
import com.nisum.backendtest.exceptions.GenericException;
import com.nisum.backendtest.mapper.UserMapper;
import com.nisum.backendtest.model.User;
import com.nisum.backendtest.repository.UserRepository;
import com.nisum.backendtest.service.JwtService;
import com.nisum.backendtest.service.UserService;
import com.nisum.backendtest.util.UserValidations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final Map<String, Object> claims = new HashMap<>();
    private static final UserMapper MAPPER = UserMapper.INSTANCE;
    private final UserValidations validations;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, UserValidations validations) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.validations = validations;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(MAPPER::userToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto saveOrUpdateUser(String id, UserRequestDto userDto) {

        boolean test = validations.validateMailAndPassword.test(userDto);
        if(!test) throw new GenericException("El formato del correo y/o la clave son incorrectos");

        validateAlreadyRegisterUser(userDto);
        claims.put("username", userDto.getName());

        User user;

        if (id != null) {
            user = userRepository.findById(UUID.fromString(id)).orElse(null);
        } else {
            user = MAPPER.userRequestToUser(userDto, this.passwordEncoder);
        }

        if (user != null) {
            MAPPER.updateUserFromRequest(userDto, user, this.passwordEncoder);
            user.setToken(jwtService.generate(claims));
            return MAPPER.userToUserResponseDto(userRepository.save(user));
        }

        return null;
    }

    private void validateAlreadyRegisterUser(UserRequestDto userDto){
        if(userRepository
                .findByNameOrEmail(userDto.getName(), userDto.getEmail())
                .isPresent())
            throw new GenericException("El usuario y mail ya existen");
    }


}
