package com.example.firstclub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DowngradeSubscriptionRequest {

    private String userId;

    private String targetTierId;
}