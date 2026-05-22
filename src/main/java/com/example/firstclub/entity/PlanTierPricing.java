package com.example.firstclub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "plan_id",
                "tier_id"
            }
        )
    }
)
public class PlanTierPricing extends BaseEntity {

    @ManyToOne
    private MembershipPlan plan;

    @ManyToOne
    private MembershipTier tier;

    private BigDecimal price;

    private String currency;
}