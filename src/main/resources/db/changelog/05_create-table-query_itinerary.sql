CREATE TABLE query_itinerary (
    itinerary_id VARCHAR(255) PRIMARY KEY,
    query_pdf_link TEXT,
    no_of_adults INT,
    no_of_childs INT,
    b2b_total_cost DECIMAL(10,2),
    b2c_total_cost DECIMAL(10,2),
    customer_id VARCHAR(255),
    CONSTRAINT fk_query_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id)
);