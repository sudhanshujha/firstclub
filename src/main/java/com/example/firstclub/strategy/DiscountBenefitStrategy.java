package com.example.firstclub.strategy;

import org.springframework.stereotype.Component;

import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitResult;
import com.example.firstclub.entity.Benefit;

@Component
public class DiscountBenefitStrategy implements BenefitStrategy {

    @Override
    public boolean supports(Benefit benefit) {
        return "DISCOUNT".equalsIgnoreCase(benefit.getType());
    }

    @Override
    public BenefitResult apply(Benefit benefit, BenefitContext context) {

        long discount = (context.getCartValue() * 10) / 100;

        return BenefitResult.builder()
                .applied(true)
                .message("10% discount applied")
                .discountAmount(discount)
                .build();
    }
}