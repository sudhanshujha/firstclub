package com.example.firstclub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

import com.example.firstclub.enums.SubscriptionStatus;

@Getter
@Setter
@Entity
public class Subscription extends BaseEntity {

    private String userId;

    @ManyToOne
    private MembershipPlan plan;

    @ManyToOne
    private MembershipTier purchasedTier;

    @ManyToOne
    private MembershipTier effectiveTier;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private Instant startDate;

    private Instant expiryDate;

    private boolean autoRenew;
}
