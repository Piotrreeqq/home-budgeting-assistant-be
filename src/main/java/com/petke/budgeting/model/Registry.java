package com.petke.budgeting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Registry {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String label;
    private String userId;
    private BigDecimal amount;

    public Registry(String label, String userId, BigDecimal amount) {
        this.label = label;
        this.userId = userId;
        this.amount = amount;
    }
}
