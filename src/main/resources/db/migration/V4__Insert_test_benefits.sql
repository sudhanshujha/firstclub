INSERT INTO BENEFIT (
    ID,
    NAME,
    TYPE,
    CONFIG_JSON,
    ACTIVE,
    CREATED_AT,
    UPDATED_AT,
    VERSION
)
VALUES 
(
    RANDOM_UUID(),
    'Free Delivery',
    'FREE_DELIVERY',
    '{}',
    TRUE,
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0
),
(
    RANDOM_UUID(),
    '10 Percent Discount',
    'DISCOUNT',
    '{\"percentage\":10}',
    TRUE,
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0
);