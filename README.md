# FirstClub Membership Platform

Backend system for a subscription-based membership platform with configurable plans, dynamic tiers, benefits, and event-driven architecture.

The project focuses on clean domain modeling, extensibility, and production-oriented backend design using Java and Spring Boot.

---
# Architecture

                REST APIs
                     ↓
             Controller Layer
                     ↓
              Service Layer
                     ↓
        Domain / Rule Engine Layer
                     ↓
            Repository Layer
                     ↓
                 Database

                     ↓

              Outbox Publisher
                     ↓
                   Kafka
                     ↓
               Async Consumers

# Relationships

    Plan
        └── multiple tiers

    Tier
        ├── multiple benefits
        └── qualification rules

    User
        └── active subscription

    Subscription
        ├── plan
        ├── purchased tier
        ├── effective tier
        └── billing cycles

    BillingCycle
        └── payment transactions

# Features

## Membership Plans

Users can subscribe to configurable plans such as:

- Monthly
- Quarterly
- Yearly

Each plan supports:

- configurable pricing
- configurable duration
- configurable tiers
- auto-renew support

---

## Membership Tiers

Users can belong to different tiers:

- Silver
- Gold
- Platinum

Tier benefits are configurable and extensible.

The system supports:

- purchased tier
- effective tier

This allows temporary tier upgrades based on qualification rules without affecting purchased entitlement.

---

## Dynamic Tier Qualification

Users can qualify for higher tiers dynamically based on metrics such as:

- monthly spend
- weekly spend
- order count
- category-specific spend
- cohorts

Examples:

```text
TOTAL_SPEND:MONTHLY > 10000
ORDER_COUNT:MONTHLY > 20
COHORT=PREMIUM_USERS

