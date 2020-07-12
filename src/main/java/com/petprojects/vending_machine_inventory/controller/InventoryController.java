package com.petprojects.vending_machine_inventory.controller;

import com.petprojects.vending_machine_inventory.dto.request.AddToSlotRequest;
import com.petprojects.vending_machine_inventory.dto.request.RemoveFromSlotRequest;
import com.petprojects.vending_machine_inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/inventory/slotCurrentData")
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService InventoryService) {
        this.inventoryService = InventoryService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllSlotsCurrentData() {
        return ResponseEntity.ok(inventoryService.getSlotsCurrentData());
    }

    @GetMapping("/{slotName}")
    public ResponseEntity<?> getSlotCurrentDataById(@PathVariable String slotName) {
        return ResponseEntity.ok(inventoryService.getSlotCurrentData(slotName));
    }

    @PutMapping
    public ResponseEntity<?> addProductsToSlot(@Valid @RequestBody AddToSlotRequest addToSlotRequest) {
        inventoryService.addProductsToSlot(addToSlotRequest.getSlotName(), addToSlotRequest.getQuantity(), addToSlotRequest.getProductId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{slotName}")
    public ResponseEntity<?> removeProductsFromSlot(@NotBlank @PathVariable String slotName,
                                                    @Valid @RequestBody RemoveFromSlotRequest removeFromSlotRequest) {
        inventoryService.removeProductsFromSlot(removeFromSlotRequest.getOperationType(), slotName, removeFromSlotRequest.getQuantity());
        return ResponseEntity.ok().build();
    }
}
