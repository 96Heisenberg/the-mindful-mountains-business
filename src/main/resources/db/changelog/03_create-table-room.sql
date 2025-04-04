CREATE TABLE room (
    room_id VARCHAR(255) PRIMARY KEY,
    room_name VARCHAR(255),
    services TEXT,
    room_tariff_b2c DECIMAL(10, 2),
    room_tariff_b2b DECIMAL(10, 2),
    property_id VARCHAR(255),
    CONSTRAINT fk_property
        FOREIGN KEY (property_id)
        REFERENCES property(property_id)
        ON DELETE CASCADE
);