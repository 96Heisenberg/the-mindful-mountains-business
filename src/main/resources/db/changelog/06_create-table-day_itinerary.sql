CREATE TABLE day_itinerary (
    day_id VARCHAR(255) PRIMARY KEY,
    rooms_opted BOOLEAN,
    transport_opted BOOLEAN,
    itinerary_day DATE,
    query_id VARCHAR(255),
    CONSTRAINT fk_day_query
        FOREIGN KEY (query_id)
        REFERENCES query_itinerary(itinerary_id)
        ON DELETE CASCADE
);