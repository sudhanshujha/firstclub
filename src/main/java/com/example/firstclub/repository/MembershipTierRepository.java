package com.example.firstclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstclub.entity.MembershipTier;

import java.util.List;
import java.util.UUID;

public interface MembershipTierRepository extends JpaRepository<MembershipTier, UUID> {

    List<MembershipTier> findByActiveTrue();
}