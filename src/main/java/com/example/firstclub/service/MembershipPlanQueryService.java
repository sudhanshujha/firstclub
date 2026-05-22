package com.example.firstclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.firstclub.dto.MembershipPlanResponse;
import com.example.firstclub.entity.MembershipPlan;
import com.example.firstclub.repository.MembershipPlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipPlanQueryService {

    private final MembershipPlanRepository repository;

    public List<MembershipPlanResponse> getPlans() {

            return repository.findAll()
                    .stream()
                    .filter(MembershipPlan::isActive)
                    .map(plan -> MembershipPlanResponse.builder()
                            .id(plan.getId().toString())
                            .name(plan.getName())
                            .durationInDays(plan.getDurationInDays())
                            .active(plan.isActive())
                            .build())
                    .toList();
        }
}