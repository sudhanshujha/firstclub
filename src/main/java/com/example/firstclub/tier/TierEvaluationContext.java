package com.example.firstclub.tier;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class TierEvaluationContext {

    /*
      Example:
      TOTAL_SPEND_MONTHLY -> 25000
      ORDER_COUNT_MONTHLY -> 12
    */
    private Map<String, String> metrics;

    private String cohort;
}