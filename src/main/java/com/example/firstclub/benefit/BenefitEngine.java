package com.example.firstclub.benefit;

import com.example.firstclub.benefit.strategy.BenefitStrategy;
import com.example.firstclub.entity.Benefit;
import com.example.firstclub.service.BenefitApplicabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitEngine {

    private final List<BenefitStrategy> strategies;

    private final BenefitApplicabilityService applicabilityService;

    public BenefitResult applyBenefit(
            Benefit benefit,
            BenefitContext context
    ) {

        boolean applicable =
                applicabilityService.isApplicable(
                        benefit,
                        context
                );

        if (!applicable) {

            return BenefitResult.builder()
                    .applied(false)
                    .message("Benefit not applicable")
                    .discountAmount(0)
                    .build();
        }

        BenefitStrategy strategy =
                strategies.stream()
                        .filter(s -> s.supports(benefit))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No strategy found"
                                )
                        );

        return strategy.apply(benefit, context);
    }
}