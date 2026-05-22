INSERT INTO plan_tier_pricing (
    id,
    created_at,
    updated_at,
    version,
    plan_id,
    tier_id,
    price,
    currency
)
SELECT
    RANDOM_UUID(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0,
    (SELECT id FROM membership_plan WHERE name = 'Basic Plan'),
    (SELECT id FROM membership_tier WHERE name = 'Bronze'),
    499,
    'INR'
UNION ALL
SELECT
    RANDOM_UUID(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0,
    (SELECT id FROM membership_plan WHERE name = 'Basic Plan'),
    (SELECT id FROM membership_tier WHERE name = 'Silver'),
    999,
    'INR';
