package com.petprojects.vending_machine_inventory.service.impl;

import com.petprojects.vending_machine_inventory.dao.ProductDAO;
import com.petprojects.vending_machine_inventory.model.Product;
import com.petprojects.vending_machine_inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCRUDService<Product, Long>
        implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        super(productDAO);
        this.productDAO = productDAO;
    }
}