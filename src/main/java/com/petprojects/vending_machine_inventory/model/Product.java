package com.petprojects.vending_machine_inventory.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotBlank
    @Length(min = 1,max = 256)
    @Column(name = "product_name", nullable = false)
    private String name;

    @DecimalMin("0.1")
    @Max(100000)
    @Column(name = "price", nullable = false)
    private double price;

    @DecimalMin("0.001")
    @Max(200)
    @Column(name = "weight", nullable = false)
    private double weight;

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
}
