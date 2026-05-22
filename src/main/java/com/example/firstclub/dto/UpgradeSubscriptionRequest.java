package com.example.firstclub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeSubscriptionRequest {

    private String userId;

    private String targetTierId;
}