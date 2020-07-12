package com.petprojects.vending_machine_inventory.dto.request;

import com.petprojects.vending_machine_inventory.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToSlotRequest {

    @NotNull
    @NotBlank
    private String slotName;

    @Min(1)
    private int quantity;

    private Long productId;
}
