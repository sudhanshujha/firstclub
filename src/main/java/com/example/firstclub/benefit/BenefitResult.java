package com.example.firstclub.benefit;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BenefitResult {

    private boolean applied;

    private String message;

    private long discountAmount;
}