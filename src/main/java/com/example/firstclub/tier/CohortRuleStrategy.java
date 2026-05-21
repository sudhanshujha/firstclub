package com.example.firstclub.tier;

import com.example.firstclub.entity.TierQualificationRule;
import org.springframework.stereotype.Component;

@Component
public class CohortRuleStrategy
        implements TierRuleStrategy {

    @Override
    public boolean supports(TierQualificationRule rule) {

        return rule.getRuleExpression()
                .startsWith("COHORT=");
    }

    @Override
    public RuleEvaluationResult evaluate(
            TierQualificationRule rule,
            TierEvaluationContext context
    ) {

        String expectedCohort =
                rule.getRuleExpression()
                        .replace("COHORT=", "");

        boolean matched =
                expectedCohort.equalsIgnoreCase(
                        context.getCohort()
                );

        return RuleEvaluationResult.builder()
                .matched(matched)
                .reason("Cohort evaluated")
                .evaluatedRule(rule.getRuleExpression())
                .build();
    }
}