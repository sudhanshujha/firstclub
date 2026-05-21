package com.example.firstclub.service;


import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitRuleEvaluator;
import com.example.firstclub.entity.Benefit;
import com.example.firstclub.entity.BenefitApplicabilityRule;
import com.example.firstclub.repository.BenefitApplicabilityRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitApplicabilityService {

    private final BenefitApplicabilityRuleRepository repository;

    private final BenefitRuleEvaluator evaluator;

    public boolean isApplicable(
            Benefit benefit,
            BenefitContext context
    ) {

        List<BenefitApplicabilityRule> rules =
                repository.findByBenefit_IdAndActiveTrue(
                        benefit.getId()
                );

        if (rules.isEmpty()) {
            return true;
        }

        return rules.stream()
                .allMatch(rule ->
                        evaluator.evaluate(rule, context)
                );
    }
}