package com.example.firstclub.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreatePlanTierPricingRequest {

    private UUID planId;

    private UUID tierId;

    private BigDecimal price;

    private String currency;
}