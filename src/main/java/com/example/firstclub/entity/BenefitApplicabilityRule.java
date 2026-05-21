package com.example.firstclub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BenefitApplicabilityRule extends BaseEntity {

    @ManyToOne
    private Benefit benefit;

    private String fieldName;

    private String operator;

    private String expectedValue;

    private boolean active;
}