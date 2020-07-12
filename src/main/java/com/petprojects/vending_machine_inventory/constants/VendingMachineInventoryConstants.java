package com.petprojects.vending_machine_inventory.constants;

import com.petprojects.vending_machine_inventory.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface VendingMachineInventoryConstants {
    String DEFAULT_ZONE = "+3";

    String DEFAULT_AUTHORITY = "USER";

    interface ResponseEntities {
        ResponseEntity BAD_REQ_USERNAME_TAKEN = new ResponseEntity<>(new ApiResponse(
                "Username is already taken!"
        ), HttpStatus.BAD_REQUEST);

        ResponseEntity USER_REGISTERED_SUCCESSFULLY = new ResponseEntity<>(new ApiResponse(
                "User registered successfully"
        ), HttpStatus.CREATED);
    }
}
