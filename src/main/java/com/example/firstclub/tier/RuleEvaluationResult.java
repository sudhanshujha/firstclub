package com.example.firstclub.tier;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RuleEvaluationResult {

    private boolean matched;

    private String reason;

    private String evaluatedRule;
}