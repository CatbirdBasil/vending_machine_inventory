package com.petprojects.vending_machine_inventory.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType;

    public JwtAuthenticationResponse(String accessToken) {
        this.tokenType = "Bearer";
        this.accessToken = accessToken;
    }
}
