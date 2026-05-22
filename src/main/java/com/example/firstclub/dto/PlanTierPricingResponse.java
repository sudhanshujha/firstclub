package com.example.firstclub.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PlanTierPricingResponse {

    private String pricingId;

    private String planId;

    private String planName;

    private String tierId;

    private String tierName;

    private BigDecimal price;

    private String currency;
}