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

            @PathVariable String userId,

            @RequestParam Long cartValue,

            @RequestParam String category,

            @RequestParam(defaultValue = "false")
            boolean premiumOrder
    ) {

        BenefitContext context = BenefitContext.builder()
                .userId(userId)
                .cartValue(cartValue)
                .category(category)
                .premiumOrder(premiumOrder)
                .build();

        return benefitService.getApplicableBenefits(
                userId,
                context
        );
    }
}