package com.example.firstclub.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MembershipPlanResponse {

    private String id;

    private String name;

    /*
      MONTHLY
      YEARLY
     */
    private String frequency;

    private int durationInDays;

    private BigDecimal basePrice;

    private boolean active;

}
