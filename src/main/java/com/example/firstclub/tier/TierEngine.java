package com.example.firstclub.tier;

import com.example.firstclub.entity.MembershipTier;
import com.example.firstclub.entity.TierQualificationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TierEngine {

    private final List<TierRuleStrategy> strategies;

    public MembershipTier evaluateHighestEligibleTier(
            TierEvaluationContext context,
            List<TierQualificationRule> rules
    ) {

        return rules.stream()

                .sorted(
                        Comparator.comparingInt(
                                TierQualificationRule::getPriority
                        ).reversed()
                )

                .filter(rule -> {

                    TierRuleStrategy strategy =
                            strategies.stream()
                                    .filter(s -> s.supports(rule))
                                    .findFirst()
                                    .orElseThrow();

                    return strategy.evaluate(
                            rule,
                            context
                    ).isMatched();
                })

                .map(TierQualificationRule::getEligibleTier)

                .findFirst()

                .orElse(null);
    }
}