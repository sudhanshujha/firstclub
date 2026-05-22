package com.example.firstclub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelSubscriptionRequest {

    private String userId;

    private String reason;
}