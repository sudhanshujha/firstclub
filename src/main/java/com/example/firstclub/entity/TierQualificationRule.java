package com.example.firstclub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class TierQualificationRule extends BaseEntity {

    @ManyToOne
    private MembershipTier eligibleTier;

    /*
     Examples:
     TOTAL_SPEND:MONTHLY > 10000
     ORDER_COUNT:MONTHLY > 10
     */
    private String ruleExpression;

    private int priority;

    private boolean active;
}