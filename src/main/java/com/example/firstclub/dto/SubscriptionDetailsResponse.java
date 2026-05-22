package com.example.firstclub.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SubscriptionDetailsResponse {

    private String subscriptionId;

    private String userId;

    private String planName;

    private String purchasedTier;

    private String effectiveTier;

    private String status;

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    private boolean autoRenew;
}