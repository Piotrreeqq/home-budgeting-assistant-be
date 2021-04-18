package com.petke.budgeting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private float amount;

    public Registry(String label, String userId, float amount) {
        this.label = label;
        this.userId = userId;
        this.amount = amount;
    }
}
