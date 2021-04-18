package com.petke.budgeting.model.response;

import com.petke.budgeting.model.Registry;
import lombok.Data;


@Data
public class RegistryResponse {

    private String id;
    private String label;
    private float amount;

    public RegistryResponse(Registry registry) {
        this.id = registry.getId();
        this.label = registry.getLabel();
        this.amount = registry.getAmount();
    }
}
