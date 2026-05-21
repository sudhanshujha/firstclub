package com.example.firstclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstclub.entity.MembershipPlan;

import java.util.List;
import java.util.UUID;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, UUID> {

    List<MembershipPlan> findByActiveTrue();
}