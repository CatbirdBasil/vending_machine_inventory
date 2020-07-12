package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.ProductSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSlotDAO extends JpaRepository<ProductSlot, Long>, ExtraProductSlotDAO {}
