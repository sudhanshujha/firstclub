package com.example.firstclub.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MembershipTier extends BaseEntity {

    private String name;

    private int level;

    private boolean active;
}
