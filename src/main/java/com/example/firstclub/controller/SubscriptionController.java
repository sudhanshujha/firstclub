package com.example.firstclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.firstclub.dto.CancelSubscriptionRequest;
import com.example.firstclub.dto.CreateSubscriptionRequest;
import com.example.firstclub.dto.DowngradeSubscriptionRequest;
import com.example.firstclub.dto.SubscriptionDetailsResponse;
import com.example.firstclub.dto.SubscriptionResponse;
import com.example.firstclub.dto.UpgradeSubscriptionRequest;
import com.example.firstclub.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;
    
    // Create Subscription
    
    @PostMapping
    public SubscriptionResponse create(@RequestBody CreateSubscriptionRequest request) {
        return service.createSubscription(request);
    }

    /*
      Current subscription
     */
    @GetMapping("/{userId}")
    public SubscriptionDetailsResponse getCurrentSubscription(
            @PathVariable String userId
    ) {

        return service.getCurrentSubscription(userId);
    }

     /*
      Cancel
     */
    @PostMapping("/cancel")
    public void cancelSubscription(
            @RequestBody
            CancelSubscriptionRequest request
    ) {

        service.cancelSubscription(request);
    }
    /*
      Upgrade tier
     */
    @PostMapping("/upgrade")
    public void upgradeSubscription(
            @RequestBody
            UpgradeSubscriptionRequest request
    ) {

        service.upgradeSubscription(request);
    }

    /*
      Downgrade tier
     */
    @PostMapping("/downgrade")
    public void downgradeSubscription(
            @RequestBody
            DowngradeSubscriptionRequest request
    ) {

        service.downgradeSubscription(request);
    }
}