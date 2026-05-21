package com.example.firstclub.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserMetric extends BaseEntity {

    private String userId;

    // Examples:
    // TOTAL_SPEND
    // ORDER_COUNT
    // ELECTRONICS_SPEND
    private String metricName;

    // Examples:
    // WEEKLY
    // MONTHLY
    // QUARTERLY
    // ROLLING_90_DAYS
    private String metricWindow;

    // Stored as string for genericity
    private String metricValue;
}