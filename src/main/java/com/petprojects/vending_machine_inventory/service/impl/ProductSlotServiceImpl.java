package com.petprojects.vending_machine_inventory.service.impl;

import com.petprojects.vending_machine_inventory.dao.ProductSlotDAO;
import com.petprojects.vending_machine_inventory.exception.InventoryException;
import com.petprojects.vending_machine_inventory.model.ProductSlot;
import com.petprojects.vending_machine_inventory.service.ProductSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSlotServiceImpl extends AbstractCRUDService<ProductSlot, Long>
        implements ProductSlotService {

    private final ProductSlotDAO productSlotDAO;

    @Autowired
    public ProductSlotServiceImpl(ProductSlotDAO productSlotDAO) {
        super(productSlotDAO);
        this.productSlotDAO = productSlotDAO;
    }

    @Override
    public ProductSlot getProductSlotByName(String slotName) {
        return productSlotDAO.getProductSlotByName(slotName)
                .orElseThrow(() -> new InventoryException("There is no product slot with name = " + slotName));
    }
}
