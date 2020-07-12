package com.petprojects.vending_machine_inventory.dto.request;

import com.petprojects.vending_machine_inventory.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveFromSlotRequest {

    @NotNull
    private OperationType operationType;

    @Min(1)
    private int quantity;
}
