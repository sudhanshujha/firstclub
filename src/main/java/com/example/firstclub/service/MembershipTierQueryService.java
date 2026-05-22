package com.example.firstclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.firstclub.dto.TierResponse;
import com.example.firstclub.repository.MembershipTierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipTierQueryService {

    private final MembershipTierRepository repository;

    public List<TierResponse> getTiers() {

        return repository.findAll()
                .stream()
                .map(tier -> TierResponse.builder()
                        .id(tier.getId().toString())
                        .name(tier.getName())
                        .level(tier.getLevel())
                        .build())
                .toList();
    }
}
