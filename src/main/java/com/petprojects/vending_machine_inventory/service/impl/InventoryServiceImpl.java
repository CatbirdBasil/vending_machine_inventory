package com.petprojects.vending_machine_inventory.service.impl;

import com.petprojects.vending_machine_inventory.dao.OperationDAO;
import com.petprojects.vending_machine_inventory.dto.ProductSlotDTO;
import com.petprojects.vending_machine_inventory.exception.InventoryException;
import com.petprojects.vending_machine_inventory.model.Operation;
import com.petprojects.vending_machine_inventory.model.OperationType;
import com.petprojects.vending_machine_inventory.model.ProductSlot;
import com.petprojects.vending_machine_inventory.service.InventoryService;
import com.petprojects.vending_machine_inventory.service.ProductService;
import com.petprojects.vending_machine_inventory.service.ProductSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {
    private OperationDAO operationDAO;
    private ProductSlotService productSlotService;
    private ProductService productService;

    @Autowired
    public InventoryServiceImpl(OperationDAO operationDAO, ProductSlotService productSlotService, ProductService productService) {
        this.operationDAO = operationDAO;
        this.productSlotService = productSlotService;
        this.productService = productService;
    }

    @Override
    public List<ProductSlotDTO> getSlotsCurrentData() {
        List<ProductSlotDTO> slotDTOS = new ArrayList<>();

        List<ProductSlot> slots = productSlotService.getAll();
        for (ProductSlot slot: slots) {
            slotDTOS.add(getSlotCurrentData(slot));
        }

        return slotDTOS;
    }

    @Override
    public ProductSlotDTO getSlotCurrentData(String slotName) {
        log.trace("getSlotCurrentData: slotName = {}", slotName);

        if (slotName == null) {
            return null;
        }

        ProductSlot slot = productSlotService.getProductSlotByName(slotName);

        return getSlotCurrentData(slot);
    }

    private ProductSlotDTO getSlotCurrentData(ProductSlot slot) {
        log.trace("getSlotCurrentData: slot = {}", slot);

        if (slot == null) {
            return null;
        }

        if (slot.getCurrProductId() == null) {
            return new ProductSlotDTO(slot);
        }

        List<Operation> operations = operationDAO.findAll();

        List<Operation> lastOperationsForSlot = operations.stream()
                .filter(operation -> operation.getSlotId() == slot.getId())
                .sorted(Comparator.comparing(Operation::getOperationTime).reversed())
                .takeWhile(operation -> operation.getProductId() == slot.getCurrProductId())
                .collect(Collectors.toList());

        return new ProductSlotDTO(
                slot,
                productService
                        .getById(slot.getCurrProductId())
                        .orElseThrow(() -> new InventoryException("Something went wrong during product retrieval")),
                countCurrentQuantity(lastOperationsForSlot)
        );
    }

    private int countCurrentQuantity(List<Operation> lastOperationsForSlot) {
        int currentQuantity = 0;

        for (Operation operation : lastOperationsForSlot) {
            currentQuantity += extractNumber(operation);
        }

        return currentQuantity;
    }

    private int extractNumber(Operation operation) {
        int quantity = operation.getQuantity();

        switch (operation.getOperationType()) {
            case ADD:
                return quantity;
            case PURCHASE:
            case RETRIEVE:
                return -quantity;
        }

        log.debug("Unknown operation: {}", operation);
        return 0;
    }

    @Override
    @Transactional
    public void addProductsToSlot(String slotName, int quantity, Long productId) {
        log.trace("addProductsToSlot: slotName = {}, quantity = {}, productId = {}", slotName, quantity, productId);

        ProductSlot slot = productSlotService.getProductSlotByName(slotName);
        validateSlotForAddition(slot, productId, quantity);

        if (slot.getCurrProductId() == null) {
            slot.setCurrProductId(productId);
        }

        Operation operation = new Operation(OperationType.ADD, slot.getId(), slot.getCurrProductId(), Instant.now(), quantity);

        operationDAO.save(operation);
    }

    private void validateSlotForAddition(ProductSlot slot, Long productId, int quantity) {
        if (slot.getCurrProductId() == null && productId == null) {
            throw new InventoryException("Can't add undefined product to empty slot");
        }

        if (productId != null && slot.getCurrProductId() != null && productId.longValue() != slot.getCurrProductId().longValue()) {
            throw new InventoryException("Required slot is already occupied by another product (productId = " + slot.getCurrProductId());
        }

        ProductSlotDTO slotDTO = getSlotCurrentData(slot);

        if (slotDTO != null && (slotDTO.getQuantity() + quantity) > slot.getMaxProducts()) {
            throw new InventoryException("There is not enough place in that slot. Currently stocked = " + slotDTO.getQuantity()
                    + ", max = " + slot.getMaxProducts());
        }
    }

    @Override
    @Transactional
    public void removeProductsFromSlot(OperationType operationType, String slotName, int quantity) {
        log.trace("addProductsToSlot: slotName = {}, quantity = {}, operationType = {}", slotName, quantity, operationType);

        if (OperationType.ADD.equals(operationType)) {
            throw new InventoryException("Incorrect data was passed for product addition");
        }

        ProductSlot slot = productSlotService.getProductSlotByName(slotName);
        ProductSlotDTO slotDTO = getSlotCurrentData(slot);
        validateSlotForRemoval(slot, slotDTO, quantity);

        Operation operation = new Operation(operationType, slot.getId(), slot.getCurrProductId(), Instant.now(), quantity);

        operationDAO.save(operation);

        if (slotDTO != null) {
            if (slotDTO.getQuantity() == quantity) {
                slot.setCurrProductId(null);
                productSlotService.save(slot);
            }
        }
    }

    private void validateSlotForRemoval(ProductSlot slot, ProductSlotDTO slotDTO, int quantity) {
        if (slot.getCurrProductId() == null) {
            throw new InventoryException("Can't remove from empty slot");
        }

        if (slotDTO != null) {
            if ((slotDTO.getQuantity() - quantity) < 0) {
                throw new InventoryException("Can't remove that amount of products from slot. Currently stocked = " + slotDTO.getQuantity());
            }
        }
    }
}
