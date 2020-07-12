package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {}
