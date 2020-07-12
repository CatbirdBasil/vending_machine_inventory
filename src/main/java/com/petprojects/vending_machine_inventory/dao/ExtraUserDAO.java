package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.User;

import java.util.Optional;

public interface ExtraUserDAO {
    Optional<User> getUserByUsername(String usernameOrEmail);
    boolean existsByUsername(String username);
}
