package com.example.firstclub.repository;

import com.example.firstclub.entity.UserMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserMetricRepository
        extends JpaRepository<UserMetric, UUID> {

    List<UserMetric> findByUserId(String userId);
}