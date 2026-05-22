package com.example.firstclub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstclub.dto.MembershipPlanResponse;
import com.example.firstclub.service.MembershipPlanQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership-plans")
public class MembershipPlanController {

    private final MembershipPlanQueryService service;

    @GetMapping
    public List<MembershipPlanResponse> getPlans() {
        return service.getPlans();
    }
}