CREATE TABLE property (
    property_id VARCHAR(255) PRIMARY KEY,
    active BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    contact_no VARCHAR(20) NOT NULL,
    email_id VARCHAR(255) UNIQUE NOT NULL
);