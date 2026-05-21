package com.example.firstclub.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.firstclub.dto.CreateSubscriptionRequest;
import com.example.firstclub.dto.SubscriptionResponse;
import com.example.firstclub.entity.MembershipPlan;
import com.example.firstclub.entity.MembershipTier;
import com.example.firstclub.entity.Subscription;
import com.example.firstclub.enums.SubscriptionStatus;
import com.example.firstclub.repository.MembershipPlanRepository;
import com.example.firstclub.repository.MembershipTierRepository;
import com.example.firstclub.repository.SubscriptionRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MembershipPlanRepository planRepository;
    private final MembershipTierRepository tierRepository;

    public SubscriptionResponse createSubscription(CreateSubscriptionRequest request) {

        // 1. Validate no active subscription
        subscriptionRepository.findByUserIdAndStatus(
                request.getUserId(),
                SubscriptionStatus.ACTIVE
        ).ifPresent(s -> {
            throw new RuntimeException("Active subscription already exists");
        });

        // 2. Fetch plan
        MembershipPlan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // 3. Fetch tier
        MembershipTier tier = tierRepository.findById(request.getTierId())
                .orElseThrow(() -> new RuntimeException("Tier not found"));

        // 4. Create subscription
        Subscription sub = new Subscription();
        sub.setUserId(request.getUserId());
        sub.setPlan(plan);

        sub.setPurchasedTier(tier);
        sub.setEffectiveTier(tier); // initial same

        sub.setStatus(SubscriptionStatus.ACTIVE);
        sub.setStartDate(Instant.now());

        sub.setExpiryDate(
                Instant.now().plusSeconds(plan.getDurationInDays() * 86400L)
        );

        sub.setAutoRenew(request.isAutoRenew());

        Subscription saved = subscriptionRepository.save(sub);

        // 5. Map response
        return mapToResponse(saved);
    }

    private SubscriptionResponse mapToResponse(Subscription sub) {

        SubscriptionResponse resp = new SubscriptionResponse();
        resp.setSubscriptionId(sub.getId());
        resp.setUserId(sub.getUserId());

        resp.setPlanName(sub.getPlan().getName());
        resp.setPurchasedTier(sub.getPurchasedTier().getName());
        resp.setEffectiveTier(sub.getEffectiveTier().getName());

        resp.setStartDate(sub.getStartDate());
        resp.setExpiryDate(sub.getExpiryDate());
        resp.setStatus(sub.getStatus().name());

        return resp;
    }
}
