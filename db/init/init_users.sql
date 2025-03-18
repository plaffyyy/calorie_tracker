CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) UNIQUE NOT NULL,
       age INT CHECK (age >= 1 AND age <= 120),
       weight INT CHECK (weight >= 30 AND weight <= 230),
       height INT CHECK (height >= 100 AND height <= 230),
       goal VARCHAR(50)
);
