package com.nisum.backendtest.service.impl;

import com.nisum.backendtest.dto.UserRequestDto;
import com.nisum.backendtest.dto.UserResponseDto;
import com.nisum.backendtest.exceptions.GenericException;
import com.nisum.backendtest.mapper.UserMapper;
import com.nisum.backendtest.model.Phone;
import com.nisum.backendtest.model.User;
import com.nisum.backendtest.repository.UserRepository;
import com.nisum.backendtest.service.JwtService;
import com.nisum.backendtest.util.UserValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = { "regex.email=^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", "regex.password=^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$" })
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private static final UserMapper MAPPER = UserMapper.INSTANCE;
    private User user;
    private UserValidations validations;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

        validations = new UserValidations("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
        userService = new UserServiceImpl(passwordEncoder, userRepository, jwtService, validations);

        userResponseDto = new UserResponseDto();
        userResponseDto.setId("aa8ebb87-b641-4fae-9e72-4d4139a0999c");
        userResponseDto.setCreated(new java.util.Date());
        userResponseDto.setModified(new java.util.Date());
        userResponseDto.setLastLogin(new java.util.Date());
        userResponseDto.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDc5OTg3fQ.GHxZgEL8wC-UtN6yOFdrNWmmEgf_sqXLU2SiN7LYanE");
        userResponseDto.setIsActive(true);

        user = new User(UUID.fromString("aa8ebb87-b641-4fae-9e72-4d4139a0999c"), "Juan Rodriguez", "user.name@domain.com", "Geeksforgeeks",
                List.of(new Phone()), new java.util.Date(), new java.util.Date(), new java.util.Date(), "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDc5OTg3fQ.GHxZgEL8wC-UtN6yOFdrNWmmEgf_sqXLU2SiN7LYanE");
   
    }

    @Test
    void getAllUsersVerifyTokenTest() {
        List<User> users = new ArrayList();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserResponseDto> allUsers = userService.getAllUsers();
        assertEquals(users.size(), allUsers.size());
    }

    @Test
    void saveOrUpdateUserExceptionTest() {
        String id = "aa8ebb87-b641-4fae-9e72-4d4139a0999c";

        userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan Rodriguez");
        userRequestDto.setEmail("user.name@domain.com");
        userRequestDto.setPassword("Geeksforgeeks");

        assertThrows(GenericException.class, () -> {
            userService.saveOrUpdateUser(id, userRequestDto);
        });

    }

}