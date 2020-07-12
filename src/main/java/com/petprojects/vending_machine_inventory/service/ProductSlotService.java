package com.petprojects.vending_machine_inventory.service;

import com.petprojects.vending_machine_inventory.dto.ProductSlotDTO;
import com.petprojects.vending_machine_inventory.model.ProductSlot;

import java.util.List;

public interface ProductSlotService extends CrudService<ProductSlot, Long> {
    ProductSlot getProductSlotByName(String slotName);
}
