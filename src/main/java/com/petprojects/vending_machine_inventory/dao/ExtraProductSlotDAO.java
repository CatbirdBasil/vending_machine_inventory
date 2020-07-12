package com.petprojects.vending_machine_inventory.dao;

import com.petprojects.vending_machine_inventory.model.ProductSlot;

import java.util.Optional;

public interface ExtraProductSlotDAO {
    Optional<ProductSlot> getProductSlotByName(String slotName);
}
