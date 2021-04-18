package com.petke.budgeting.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class RechargeRequest {

    @Min(value = 0, message = "Recharge amount must be greater or equal to 0")
    private BigDecimal amount;
}
