package com.example.firstclub.repository;


import com.example.firstclub.entity.TierBenefitMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TierBenefitMappingRepository
        extends JpaRepository<TierBenefitMapping, UUID> {

    List<TierBenefitMapping> findByTier_Id(UUID tierId);
}