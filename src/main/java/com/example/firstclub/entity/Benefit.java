package com.example.firstclub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Benefit extends BaseEntity {

    private String name;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String configJson;

    private boolean active;
}
