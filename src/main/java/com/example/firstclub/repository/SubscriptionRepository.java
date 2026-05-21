package com.example.firstclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstclub.entity.Subscription;
import com.example.firstclub.enums.SubscriptionStatus;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    Optional<Subscription> findByUserIdAndStatus(String userId, SubscriptionStatus status);
}