package com.example.firstclub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TierBenefitMapping extends BaseEntity {

    @ManyToOne
    private MembershipTier tier;

    @ManyToOne
    private Benefit benefit;

    private boolean active;
}