CREATE TABLE booking (
    booking_id UUID PRIMARY KEY,
    query_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    query_snapshot JSONB NOT NULL,

    b2b_total NUMERIC,
    b2c_total NUMERIC,

    additional_costs NUMERIC,
    discount_amount NUMERIC,
    final_cost NUMERIC,
    advance_paid NUMERIC,
    due NUMERIC,

    summary TEXT,
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
