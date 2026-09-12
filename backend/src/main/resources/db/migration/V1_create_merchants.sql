CREATE TABLE merchants (
    id BIGINT GENERATED ALWAYS AS IDENTITY,

    public_id UUID NOT NULL,

    business_name VARCHAR(150) NOT NULL,

    email VARCHAR(254) NOT NULL,

    status VARCHAR(20) NOT NULL,

    settlement_currency VARCHAR(3) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT pk_merchants
        PRIMARY KEY (id),

    CONSTRAINT uk_merchants_public_id
        UNIQUE (public_id),

    CONSTRAINT chk_merchants_business_name_not_blank
        CHECK (char_length(trim(business_name)) > 0),

    CONSTRAINT chk_merchants_email_not_blank
        CHECK (char_length(trim(email)) > 0),

    CONSTRAINT chk_merchants_status
        CHECK (status IN ('ACTIVE', 'INACTIVE')),

    CONSTRAINT chk_merchants_settlement_currency
        CHECK (settlement_currency ~ '^[A-Z]{3}$'),

    CONSTRAINT chk_merchants_timestamps
        CHECK (updated_at >= created_at)
);