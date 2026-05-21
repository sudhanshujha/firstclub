package com.example.firstclub.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstclub.service.TierEvaluationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tier-evaluation")
@RequiredArgsConstructor
public class TierEvaluationController {

    private final TierEvaluationService service;

    @PostMapping("/{userId}")
    public void evaluate(@PathVariable String userId) {
        service.evaluateUser(userId);
    }
}