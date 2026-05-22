package com.example.firstclub.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.firstclub.dto.CancelSubscriptionRequest;
import com.example.firstclub.dto.CreateSubscriptionRequest;
import com.example.firstclub.dto.DowngradeSubscriptionRequest;
import com.example.firstclub.dto.SubscriptionDetailsResponse;
import com.example.firstclub.dto.SubscriptionResponse;
import com.example.firstclub.dto.UpgradeSubscriptionRequest;
import com.example.firstclub.entity.MembershipPlan;
import com.example.firstclub.entity.MembershipTier;
import com.example.firstclub.entity.Subscription;
import com.example.firstclub.enums.SubscriptionStatus;
import com.example.firstclub.repository.MembershipPlanRepository;
import com.example.firstclub.repository.MembershipTierRepository;
import com.example.firstclub.repository.SubscriptionRepository;

import java.util.UUID;
import java.time.Instant;
import java.time.ZoneId;

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
    //Get Current Subscription
    public SubscriptionDetailsResponse getCurrentSubscription(String userId) 
    {

        Subscription subscription =
                subscriptionRepository
                        .findByUserIdAndStatus(
                                userId,
                                SubscriptionStatus.ACTIVE
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No active subscription found"
                                )
                        );

        return map(subscription);
    }
    // Cancel Subscription
    @Transactional
    public void cancelSubscription(CancelSubscriptionRequest request) {

        Subscription subscription =
                subscriptionRepository
                        .findByUserIdAndStatus(
                                request.getUserId(),
                                SubscriptionStatus.ACTIVE
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No active subscription found"
                                )
                        );

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );

        subscriptionRepository.save(subscription);

        /*
        publish event later
        */
    }
    
    // upgrade subscription
    @Transactional
    public void upgradeSubscription(UpgradeSubscriptionRequest request) {

        Subscription subscription =
                subscriptionRepository
                        .findByUserIdAndStatus(
                                request.getUserId(),
                                SubscriptionStatus.ACTIVE
                        )
                        .orElseThrow();

        MembershipTier targetTier =
                tierRepository.findById(
                        UUID.fromString(
                                request.getTargetTierId()
                        )
                ).orElseThrow();

        /*
        Validation:
        only upward movement allowed
        */

        if (targetTier.getLevel() <=
                subscription.getPurchasedTier().getLevel()) {

            throw new RuntimeException(
                    "Target tier must be higher"
            );
        }

        subscription.setPurchasedTier(targetTier);

        /*
        purchased tier changed
        effective tier should also move
        */

        if (targetTier.getLevel() >
                subscription.getEffectiveTier().getLevel()) {

            subscription.setEffectiveTier(targetTier);
        }

        subscriptionRepository.save(subscription);
    }

    // downgrade subscription
    @Transactional
public void downgradeSubscription(
        DowngradeSubscriptionRequest request
) {

    Subscription subscription =
            subscriptionRepository
                    .findByUserIdAndStatus(
                            request.getUserId(),
                            SubscriptionStatus.ACTIVE
                    )
                    .orElseThrow();

    MembershipTier targetTier =
            tierRepository.findById(
                    UUID.fromString(
                            request.getTargetTierId()
                    )
            ).orElseThrow();

    /*
      Validation:
      only downward movement allowed
     */

    if (targetTier.getLevel() >=
            subscription.getPurchasedTier().getLevel()) {

        throw new RuntimeException(
                "Target tier must be lower"
        );
    }

    subscription.setPurchasedTier(targetTier);

    /*
      effective tier recalculated safely
     */

    if (subscription.getEffectiveTier().getLevel()
            > targetTier.getLevel()) {

        subscription.setEffectiveTier(
                targetTier
        );
    }

    subscriptionRepository.save(subscription);
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

    private SubscriptionDetailsResponse map(Subscription subscription) {

        return SubscriptionDetailsResponse.builder()
                .subscriptionId(
                        subscription.getId().toString()
                )
                .userId(subscription.getUserId())
                .planName(
                        subscription.getPlan().getName()
                )
                .purchasedTier(
                        subscription.getPurchasedTier()
                                .getName()
                )
                .effectiveTier(
                        subscription.getEffectiveTier()
                                .getName()
                )
                .status(
                        subscription.getStatus().name()
                )
                .startDate(
                        subscription.getStartDate().atZone(ZoneId.systemDefault()).toLocalDateTime()
                )
                .expiryDate(
                        subscription.getExpiryDate().atZone(ZoneId.systemDefault()).toLocalDateTime()
                )
                .autoRenew(
                        subscription.isAutoRenew()
                )
                .build();
    }
}
