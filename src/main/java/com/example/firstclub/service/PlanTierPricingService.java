package com.example.firstclub.service;

import com.example.firstclub.dto.CreatePlanTierPricingRequest;
import com.example.firstclub.dto.PlanTierPricingResponse;
import com.example.firstclub.entity.MembershipPlan;
import com.example.firstclub.entity.MembershipTier;
import com.example.firstclub.entity.PlanTierPricing;
import com.example.firstclub.repository.MembershipPlanRepository;
import com.example.firstclub.repository.MembershipTierRepository;
import com.example.firstclub.repository.PlanTierPricingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanTierPricingService {

    private final PlanTierPricingRepository pricingRepository;

    private final MembershipPlanRepository planRepository;

    private final MembershipTierRepository tierRepository;

    public PlanTierPricingResponse create(
            CreatePlanTierPricingRequest request
    ) {

        MembershipPlan plan =
                planRepository.findById(
                        request.getPlanId()
                ).orElseThrow();

        MembershipTier tier =
                tierRepository.findById(
                        request.getTierId()
                ).orElseThrow();

        pricingRepository.findByPlanIdAndTierId(
                plan.getId(),
                tier.getId()
        ).ifPresent(existing -> {

            throw new RuntimeException(
                    "Pricing already exists"
            );
        });

        PlanTierPricing pricing =
                new PlanTierPricing();

        pricing.setPlan(plan);

        pricing.setTier(tier);

        pricing.setPrice(request.getPrice());

        pricing.setCurrency(request.getCurrency());

        PlanTierPricing saved =
                pricingRepository.save(pricing);

        return map(saved);
    }

    public List<PlanTierPricingResponse>
    getPricingByPlan(String planId) {

        return pricingRepository.findByPlanId(
                        java.util.UUID.fromString(planId)
                )
                .stream()
                .map(this::map)
                .toList();
    }

    private PlanTierPricingResponse map(
            PlanTierPricing pricing
    ) {

        return PlanTierPricingResponse.builder()
                .pricingId(
                        pricing.getId().toString()
                )
                .planId(
                        pricing.getPlan().getId().toString()
                )
                .planName(
                        pricing.getPlan().getName()
                )
                .tierId(
                        pricing.getTier().getId().toString()
                )
                .tierName(
                        pricing.getTier().getName()
                )
                .price(pricing.getPrice())
                .currency(pricing.getCurrency())
                .build();
    }
}