package com.petprojects.vending_machine_inventory.service;

import com.petprojects.vending_machine_inventory.dto.ProductSlotDTO;
import com.petprojects.vending_machine_inventory.model.OperationType;

import java.util.List;

public interface InventoryService {
    ProductSlotDTO getSlotCurrentData(String slotName);
    List<ProductSlotDTO> getSlotsCurrentData();
    void addProductsToSlot(String slotName, int quantity, Long productId);
    void removeProductsFromSlot(OperationType operationType, String slotName, int quantity);
}
