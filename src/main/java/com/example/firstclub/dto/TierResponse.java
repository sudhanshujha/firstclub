package com.example.firstclub.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TierResponse {

    private String id;

    private String name;

    private int level;

    private String description;
}