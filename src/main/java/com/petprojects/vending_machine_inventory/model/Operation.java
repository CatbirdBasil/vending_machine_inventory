package com.petprojects.vending_machine_inventory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "operation_type_id", nullable = false)
    private long operationTypeId;

    @Column(name = "slot_id", nullable = false)
    private long slotId;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "operation_time", nullable = false)
    private Instant operationTime;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Operation(long operationTypeId, long slotId, long productId, Instant operationTime) {
        this.operationTypeId = operationTypeId;
        this.slotId = slotId;
        this.productId = productId;
        this.operationTime = operationTime;
        this.quantity = 1;
    }

    public Operation(OperationType operationType, long slotId, long productId, Instant operationTime) {
        this(1, slotId, productId, operationTime);
        setOperationType(operationType);
    }

    public Operation(long operationTypeId, long slotId, long productId, Instant operationTime, int quantity) {
        this(operationTypeId, slotId, productId, operationTime);
        this.quantity = quantity;
    }

    public Operation(OperationType operationType, long slotId, long productId, Instant operationTime, int quantity) {
        this(operationType, slotId, productId, operationTime);
        this.quantity = quantity;
    }

    public OperationType getOperationType() {
        return OperationType.valueOf(operationTypeId);
    }

    public void setOperationType(OperationType type) {
        operationTypeId = type.getId();
    }
}
