INSERT INTO tier_benefit_mapping (id, tier_id, benefit_id, active, created_at, updated_at, version)
SELECT 
    RANDOM_UUID(),
    (SELECT id FROM membership_tier WHERE name = 'Bronze'),
    (SELECT id FROM benefit WHERE type = 'FREE_DELIVERY'),
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    0
UNION ALL
SELECT 
    RANDOM_UUID(),
    (SELECT id FROM membership_tier WHERE name = 'Bronze'),
    (SELECT id FROM benefit WHERE type = 'DISCOUNT'),
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    0;