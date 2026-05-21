INSERT INTO BENEFIT_APPLICABILITY_RULE (
    ID,
    BENEFIT_ID,
    FIELD_NAME,
    OPERATOR,
    EXPECTED_VALUE,
    ACTIVE,
    CREATED_AT,
    UPDATED_AT,
    VERSION
)
VALUES (
    RANDOM_UUID(),
    select id from benefit where type = 'DISCOUNT',
    'cartValue',
    'GREATER_THAN',
    '1000',
    TRUE,
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0
),
(
    RANDOM_UUID(),
    select id from benefit where type = 'DISCOUNT',
    'category',
    'EQUALS',
    'ELECTRONICS',
    TRUE,
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    0
);