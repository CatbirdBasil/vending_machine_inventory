package com.petprojects.vending_machine_inventory.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OperationType {
    ADD(1L),
    RETRIEVE(2L),
    PURCHASE(3L);

    private final long id;

    OperationType(long id) {
        this.id = id;
    }

    @JsonCreator
    public static OperationType decode(final String code) {
        return Stream.of(OperationType.values())
                .filter(targetEnum -> targetEnum.name().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    public static OperationType valueOf(long id) {
        for (OperationType type : values()) {
            if (type.id == id) {
                return type;
            }
        }

        return null;
    }
}
