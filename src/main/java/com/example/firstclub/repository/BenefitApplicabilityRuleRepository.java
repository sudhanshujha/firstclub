package com.example.firstclub.repository;

import com.example.firstclub.entity.BenefitApplicabilityRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BenefitApplicabilityRuleRepository
        extends JpaRepository<BenefitApplicabilityRule, UUID> {

    List<BenefitApplicabilityRule>
    findByBenefit_IdAndActiveTrue(UUID benefitId);
}