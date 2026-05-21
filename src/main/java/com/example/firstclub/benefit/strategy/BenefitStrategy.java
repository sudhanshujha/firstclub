package com.example.firstclub.benefit.strategy;

import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitResult;
import com.example.firstclub.entity.Benefit;

public interface BenefitStrategy {

    boolean supports(Benefit benefit);

    BenefitResult apply(Benefit benefit, BenefitContext context);
}
