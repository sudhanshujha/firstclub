-- Insert test membership plans
INSERT INTO membership_plan (id, name, duration_in_days, active, created_at, updated_at, version)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001', 'Basic Plan', 30, true, NOW(), NOW(), 0),
    ('550e8400-e29b-41d4-a716-446655440002', 'Standard Plan', 90, true, NOW(), NOW(), 0),
    ('550e8400-e29b-41d4-a716-446655440003', 'Premium Plan', 365, true, NOW(), NOW(), 0),
    ('550e8400-e29b-41d4-a716-446655440004', 'Annual Plan', 365, true, NOW(), NOW(), 0);
