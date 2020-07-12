package com.petprojects.vending_machine_inventory.controller;

import com.petprojects.vending_machine_inventory.model.ProductSlot;
import com.petprojects.vending_machine_inventory.service.ProductSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/inventory/productSlot")
@Slf4j
public class ProductSlotController {
    private final ProductSlotService productSlotService;

    @Autowired
    public ProductSlotController(ProductSlotService productSlotService) {
        this.productSlotService = productSlotService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllProductSlots() {
        return ResponseEntity.ok(productSlotService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductSlotById(@PathVariable long id) {
        return ResponseEntity.ok(productSlotService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> createProductSlot(@Valid @RequestBody ProductSlot productSlot) {
        return ResponseEntity.ok(productSlotService.save(productSlot));
    }

    @PutMapping
    public ResponseEntity<?> updateProductSlot(@Valid @RequestBody ProductSlot productSlot) {
        productSlotService.save(productSlot);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductSlot(@PathVariable long id) {
        productSlotService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
