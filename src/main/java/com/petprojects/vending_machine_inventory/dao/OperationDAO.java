package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDAO extends JpaRepository<Operation, Long> {}
