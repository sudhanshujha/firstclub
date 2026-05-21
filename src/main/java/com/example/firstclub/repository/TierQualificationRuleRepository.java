package com.example.firstclub.repository;

import com.example.firstclub.entity.TierQualificationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TierQualificationRuleRepository
        extends JpaRepository<TierQualificationRule, UUID> {

    List<TierQualificationRule> findByActiveTrue();
}