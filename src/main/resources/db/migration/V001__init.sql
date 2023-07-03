CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) NOT NULL UNIQUE,
    name         VARCHAR(255) NOT NULL,
    surname      VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL,
    address_id   INTEGER,
    age          INTEGER
);

CREATE TABLE addresses
(
    id         SERIAL PRIMARY KEY,
    street     VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    state      VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    country    VARCHAR(255) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT fk_address_id
        FOREIGN KEY (address_id)
            REFERENCES addresses (id);