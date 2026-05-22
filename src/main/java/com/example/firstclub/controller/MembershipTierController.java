package com.example.firstclub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstclub.dto.TierResponse;
import com.example.firstclub.service.MembershipTierQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership-tiers")
public class MembershipTierController {

    private final MembershipTierQueryService service;

    @GetMapping
    public List<TierResponse> getTiers() {
        return service.getTiers();
    }
}
