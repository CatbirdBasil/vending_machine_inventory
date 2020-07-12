package com.petprojects.vending_machine_inventory.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Product_Slot")
public class ProductSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Length(min = 1, max = 32)
    @Column(name = "slot_name", nullable = false)
    private String name;

    @Min(1)
    @Max(1000)
    @Column(name = "max_products", nullable = false)
    private int maxProducts;

    @Min(1)
    @Max(1000)
    @Column(name = "shelf", nullable = true)
    private long shelf;

    @Column(name = "curr_product_id", nullable = true)
    private Long currProductId;

    public ProductSlot(String name, int maxProducts) {
        this.name = name;
        this.maxProducts = maxProducts;
    }

    public ProductSlot(String name, int maxProducts, long shelf) {
        this(name, maxProducts);
        this.shelf = shelf;
    }
}
