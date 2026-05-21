INSERT INTO tier_qualification_rule (
    id,
    created_at,
    updated_at,
    version,
    eligible_tier_id,
    rule_expression,
    priority,
    active
)
VALUES (
    RANDOM_UUID(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0,
    '660e8400-e29b-41d4-a716-446655440002',
    'TOTAL_SPEND:MONTHLY > 10000',
    1,
    true
);