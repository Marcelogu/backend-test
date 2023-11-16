package com.nisum.backendtest.repository;

import com.nisum.backendtest.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByName(String name);
    Optional<User> findByNameOrEmail(String name, String email);
    List<User> findAll();

}
