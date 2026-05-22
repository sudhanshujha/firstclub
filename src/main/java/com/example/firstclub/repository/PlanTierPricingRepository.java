package com.example.firstclub.repository;

import com.example.firstclub.entity.PlanTierPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanTierPricingRepository
        extends JpaRepository<PlanTierPricing, UUID> {

    Optional<PlanTierPricing>
    findByPlanIdAndTierId(
            UUID planId,
            UUID tierId
    );

    List<PlanTierPricing>
    findByPlanId(UUID planId);
}