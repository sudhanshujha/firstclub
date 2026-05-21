INSERT INTO user_metric (
  id,
  user_id,
  metric_name,
  metric_window,
  metric_value
)
VALUES (
  RANDOM_UUID(),
  '760e8400-e29b-41d4-a716-446655440001',
  'TOTAL_SPEND',
  'MONTHLY',
  '15000'
);