package com.example.firstclub.benefit;

import com.example.firstclub.entity.Benefit;
import com.example.firstclub.strategy.BenefitStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitEngine {

    private final List<BenefitStrategy> strategies;

    public BenefitResult applyBenefit(
            Benefit benefit,
            BenefitContext context
    ) {

        BenefitStrategy strategy = strategies.stream()
                .filter(s -> s.supports(benefit))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No strategy found for benefit type"
                        )
                );

        return strategy.apply(benefit, context);
    }
}