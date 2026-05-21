package com.example.firstclub.service;


import com.example.firstclub.benefit.*;
import com.example.firstclub.entity.Subscription;
import com.example.firstclub.entity.TierBenefitMapping;
import com.example.firstclub.repository.SubscriptionRepository;
import com.example.firstclub.repository.TierBenefitMappingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitService {

    private final SubscriptionRepository subscriptionRepository;
    private final TierBenefitMappingRepository mappingRepository;
    private final BenefitEngine benefitEngine;

    public List<BenefitResult> getApplicableBenefits(
            String userId,
            BenefitContext context
    ) {

        Subscription subscription =
                subscriptionRepository
                        .findByUserIdAndStatus(
                                userId,
                                com.example.firstclub.enums.SubscriptionStatus.ACTIVE
                        )
                        .orElseThrow(() ->
                                new RuntimeException("No active subscription")
                        );

        List<TierBenefitMapping> mappings =
                mappingRepository.findByTier_Id(
                        subscription.getEffectiveTier().getId()
                );

        return mappings.stream()
                .map(TierBenefitMapping::getBenefit)
                .map(b -> benefitEngine.applyBenefit(b, context))
                .toList();
    }
}