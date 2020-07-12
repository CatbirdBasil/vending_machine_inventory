package com.petprojects.vending_machine_inventory.dto;

import com.petprojects.vending_machine_inventory.model.Product;
import com.petprojects.vending_machine_inventory.model.ProductSlot;
import lombok.Data;

@Data
public class ProductSlotDTO {
   private ProductSlot slot;
   private Product product;
   private long quantity;

   public ProductSlotDTO(ProductSlot slot) {
       this.slot = slot;
       this.product = null;
       this.quantity = 0;
   }

    public ProductSlotDTO(ProductSlot slot, Product product, long quantity) {
        this.slot = slot;
        this.product = product;
        this.quantity = quantity;
    }
}
