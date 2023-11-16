package com.nisum.backendtest.repository;

import com.nisum.backendtest.model.Phone;
import com.nisum.backendtest.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    UserRepository userRepository;

    Optional<User> user;

    List<User> users = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        user = Optional.of(new User(UUID.fromString("aa8ebb87-b641-4fae-9e72-4d4139a0999c"), "Juan Rodriguez", "user.name@domain.com", "Geeksforgeeks",
                List.of(new Phone()), new java.util.Date(), new java.util.Date(), new java.util.Date(), "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDc5OTg3fQ.GHxZgEL8wC-UtN6yOFdrNWmmEgf_sqXLU2SiN7LYanE"));

        users.add(new User(UUID.fromString("aa8ebb87-b641-4fae-9e72-4d4139a0999c"), "Juan Rodriguez", "user.name@domain.com", "Geeksforgeeks",
                List.of(new Phone()), new java.util.Date(), new java.util.Date(), new java.util.Date(), "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDc5OTg3fQ.GHxZgEL8wC-UtN6yOFdrNWmmEgf_sqXLU2SiN7LYanE"));
    }

    @Test
    void findByName() {
        when(userRepository.findById(UUID.fromString("aa8ebb87-b641-4fae-9e72-4d4139a0999c"))).thenReturn(user);

        assertEquals("Juan Rodriguez", userRepository
                .findById(UUID.fromString("aa8ebb87-b641-4fae-9e72-4d4139a0999c"))
                .get()
                .getName());
    }

    @Test
    void findByNameOrEmail() {
        when(userRepository.findByNameOrEmail("Juan Rodriguez", "user.name@domain.com")).thenReturn(user);

        assertEquals("user.name@domain.com", userRepository
                .findByNameOrEmail("Juan Rodriguez", "user.name@domain.com")
                .get()
                .getEmail());
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(users);

        assertEquals(user.get(), userRepository.findAll().get(0));
    }
}