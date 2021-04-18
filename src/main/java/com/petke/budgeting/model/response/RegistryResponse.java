package com.petke.budgeting.model.response;

import com.petke.budgeting.model.Registry;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class RegistryResponse {

    private String id;
    private String label;
    private BigDecimal amount;

    public RegistryResponse(Registry registry) {
        this.id = registry.getId();
        this.label = registry.getLabel();
        this.amount = registry.getAmount();
    }
}
