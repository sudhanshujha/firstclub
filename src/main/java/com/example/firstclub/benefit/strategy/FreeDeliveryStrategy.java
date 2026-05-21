package com.example.firstclub.benefit.strategy;

import org.springframework.stereotype.Component;

import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitResult;
import com.example.firstclub.entity.Benefit;

@Component
public class FreeDeliveryStrategy implements BenefitStrategy {

    @Override
    public boolean supports(Benefit benefit) {
        return "FREE_DELIVERY".equalsIgnoreCase(benefit.getType());
    }

    @Override
    public BenefitResult apply(Benefit benefit, BenefitContext context) {

        return BenefitResult.builder()
                .applied(true)
                .message("Free delivery applied")
                .discountAmount(100)
                .build();
    }
}
