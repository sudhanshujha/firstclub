-- Insert test membership tiers
INSERT INTO membership_tier (id, name, level, active, created_at, updated_at, version)
VALUES
    ('660e8400-e29b-41d4-a716-446655440001', 'Bronze', 1, true, NOW(), NOW(), 0),
    ('660e8400-e29b-41d4-a716-446655440002', 'Silver', 2, true, NOW(), NOW(), 0),
    ('660e8400-e29b-41d4-a716-446655440003', 'Gold', 3, true, NOW(), NOW(), 0),
    ('660e8400-e29b-41d4-a716-446655440004', 'Platinum', 4, true, NOW(), NOW(), 0);
