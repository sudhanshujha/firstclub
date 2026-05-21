package com.example.firstclub.dto;

import java.time.Instant;
import java.util.UUID;

public class SubscriptionResponse {

    private UUID subscriptionId;
    private String userId;

    private String planName;
    private String purchasedTier;
    private String effectiveTier;

    private Instant startDate;
    private Instant expiryDate;

    private String status;

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPurchasedTier() {
        return purchasedTier;
    }

    public void setPurchasedTier(String purchasedTier) {
        this.purchasedTier = purchasedTier;
    }

    public String getEffectiveTier() {
        return effectiveTier;
    }

    public void setEffectiveTier(String effectiveTier) {
        this.effectiveTier = effectiveTier;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getters/setters
}
