package com.example.firstclub.controller;

import com.example.firstclub.dto.CreatePlanTierPricingRequest;
import com.example.firstclub.dto.PlanTierPricingResponse;
import com.example.firstclub.service.PlanTierPricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan-tier-pricing")
public class PlanTierPricingController {

    private final PlanTierPricingService service;

    @PostMapping
    public PlanTierPricingResponse create(
            @RequestBody
            CreatePlanTierPricingRequest request
    ) {

        return service.create(request);
    }

    @GetMapping("/plans/{planId}")
    public List<PlanTierPricingResponse>
    getPricingByPlan(
            @PathVariable String planId
    ) {

        return service.getPricingByPlan(planId);
    }
}