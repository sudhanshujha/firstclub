package com.example.firstclub.tier;

import com.example.firstclub.entity.TierQualificationRule;

public interface TierRuleStrategy {

    boolean supports(TierQualificationRule rule);

    RuleEvaluationResult evaluate(
            TierQualificationRule rule,
            TierEvaluationContext context
    );
}