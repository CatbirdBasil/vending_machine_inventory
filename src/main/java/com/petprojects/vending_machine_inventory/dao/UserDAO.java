package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserDAO extends JpaRepository<User, Long>, ExtraUserDAO {}
