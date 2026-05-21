package com.example.firstclub.benefit;

import com.example.firstclub.entity.BenefitApplicabilityRule;
import org.springframework.stereotype.Component;

@Component
public class BenefitRuleEvaluator {

    public boolean evaluate(
            BenefitApplicabilityRule rule,
            BenefitContext context
    ) {

        Object actualValue = extractFieldValue(
                rule.getFieldName(),
                context
        );

        return switch (rule.getOperator()) {

            case "EQUALS" ->
                    actualValue.toString()
                            .equalsIgnoreCase(
                                    rule.getExpectedValue()
                            );

            case "GREATER_THAN" ->
                    Long.parseLong(actualValue.toString()) >
                            Long.parseLong(rule.getExpectedValue());

            case "LESS_THAN" ->
                    Long.parseLong(actualValue.toString()) <
                            Long.parseLong(rule.getExpectedValue());

            default -> false;
        };
    }

    private Object extractFieldValue(
            String fieldName,
            BenefitContext context
    ) {

        return switch (fieldName) {

            case "category" -> context.getCategory();

            case "cartValue" -> context.getCartValue();

            case "premiumOrder" -> context.isPremiumOrder();

            default -> throw new RuntimeException(
                    "Unsupported field: " + fieldName
            );
        };
    }
}