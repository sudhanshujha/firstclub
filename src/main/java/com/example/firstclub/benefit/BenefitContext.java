package com.example.firstclub.benefit;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BenefitContext {

    private String userId;

    private String category;

    private long cartValue;

    private boolean premiumOrder;
}