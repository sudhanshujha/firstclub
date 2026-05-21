package com.example.firstclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.firstclub.dto.CreateSubscriptionRequest;
import com.example.firstclub.dto.SubscriptionResponse;
import com.example.firstclub.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponse create(@RequestBody CreateSubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }
}