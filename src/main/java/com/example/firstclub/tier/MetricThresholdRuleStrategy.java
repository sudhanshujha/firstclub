package com.example.firstclub.tier;


import com.example.firstclub.entity.TierQualificationRule;
import org.springframework.stereotype.Component;

@Component
public class MetricThresholdRuleStrategy
        implements TierRuleStrategy {

    @Override
    public boolean supports(TierQualificationRule rule) {

        return rule.getRuleExpression().contains(">");
    }

    @Override
    public RuleEvaluationResult evaluate(
            TierQualificationRule rule,
            TierEvaluationContext context
    ) {

        String expression = rule.getRuleExpression();

        /*
          Example:
          TOTAL_SPEND:MONTHLY > 10000
         */

        String[] parts = expression.split(">");

        String metricKey =
                parts[0]
                        .trim()
                        .replace(":", "_");

        long threshold =
                Long.parseLong(parts[1].trim());

        long actualValue =
                Long.parseLong(
                        context.getMetrics()
                                .getOrDefault(metricKey, "0")
                );

        boolean matched = actualValue > threshold;

        return RuleEvaluationResult.builder()
                .matched(matched)
                .reason("Metric threshold evaluated")
                .evaluatedRule(expression)
                .build();
    }
}