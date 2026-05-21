package com.example.firstclub.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MembershipPlan extends BaseEntity {

    private String name;

    private int durationInDays;

    private boolean active;
}