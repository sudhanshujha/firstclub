package com.example.firstclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstclub.entity.PlanTierPricing;

import java.util.List;
import java.util.UUID;

public interface PlanTierPricingRepository extends JpaRepository<PlanTierPricing, UUID> {

    List<PlanTierPricing> findByPlan_Id(UUID planId);
}