package com.example.firstclub.controller;

import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitResult;
import com.example.firstclub.service.BenefitService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benefits")
@RequiredArgsConstructor
public class BenefitController {

    private final BenefitService benefitService;

    @GetMapping("/{userId}")
    public List<BenefitResult> getBenefits(
            @PathVariable String userId
    ) {

        BenefitContext context = BenefitContext.builder()
                .cartValue(1000)
                .category("ELECTRONICS")
                .premiumOrder(true)
                .userId(userId)
                .build();

        return benefitService.getApplicableBenefits(
                userId,
                context
        );
    }
}