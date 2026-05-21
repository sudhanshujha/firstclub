package com.example.firstclub.service;

import com.example.firstclub.entity.MembershipTier;
import com.example.firstclub.entity.Subscription;
import com.example.firstclub.entity.UserMetric;
import com.example.firstclub.enums.SubscriptionStatus;
import com.example.firstclub.repository.SubscriptionRepository;
import com.example.firstclub.repository.TierQualificationRuleRepository;
import com.example.firstclub.repository.UserMetricRepository;
import com.example.firstclub.tier.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TierEvaluationService {

    private final UserMetricRepository metricRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final TierQualificationRuleRepository ruleRepository;

    private final TierEngine tierEngine;

    public void evaluateUser(String userId) {

        Subscription subscription =
                subscriptionRepository
                        .findByUserIdAndStatus(
                                userId,
                                SubscriptionStatus.ACTIVE
                        )
                        .orElseThrow();

        List<UserMetric> metrics =
                metricRepository.findByUserId(userId);

        Map<String, String> metricMap =
                metrics.stream()
                        .collect(Collectors.toMap(
                                m -> m.getMetricName()
                                        + "_"
                                        + m.getMetricWindow(),

                                UserMetric::getMetricValue
                        ));

        TierEvaluationContext context =
                TierEvaluationContext.builder()
                        .metrics(metricMap)
                        .build();

        MembershipTier eligibleTier =
                tierEngine.evaluateHighestEligibleTier(
                        context,
                        ruleRepository.findByActiveTrue()
                );

        /*
        If no rule matched,
        fallback to purchased tier
        */

        if (eligibleTier == null) {

                return;
        }

        /*
        IMPORTANT BUSINESS RULE

        effectiveTier =
                max(
                purchasedTier,
                eligibleTier
                )
        */

        MembershipTier purchasedTier =
                subscription.getPurchasedTier();

        MembershipTier finalEffectiveTier;

        if (eligibleTier.getLevel() >
                purchasedTier.getLevel()) {

                finalEffectiveTier = eligibleTier;

        } else {

                finalEffectiveTier = purchasedTier;
        }

        subscription.setEffectiveTier(finalEffectiveTier);

        subscriptionRepository.save(subscription);
        }
}