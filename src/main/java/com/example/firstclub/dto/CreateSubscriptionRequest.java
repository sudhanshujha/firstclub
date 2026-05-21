package com.example.firstclub.dto;

import java.util.UUID;

public class CreateSubscriptionRequest {

    private String userId;

    private UUID planId;

    private UUID tierId;

    private boolean autoRenew;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public UUID getTierId() {
        return tierId;
    }

    public void setTierId(UUID tierId) {
        this.tierId = tierId;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    // getters and setters
}
