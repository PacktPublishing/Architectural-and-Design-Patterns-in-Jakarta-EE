CREATE TABLE users (
    id SERIAL NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255),

    PRIMARY KEY(id),
);