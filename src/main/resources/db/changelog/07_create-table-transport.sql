CREATE TABLE transport (
    transport_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    contact_no VARCHAR(50),
    booking_via VARCHAR(50),
    b2c_tariff DECIMAL(10,2),
    b2b_tariff DECIMAL(10,2),
    day_id VARCHAR(255),
    CONSTRAINT fk_transport_day
        FOREIGN KEY (day_id)
        REFERENCES day_itinerary(day_id)
        ON DELETE CASCADE
);