
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_name VARCHAR(255),
    user_role VARCHAR(255),
    FOREIGN KEY (user_role) REFERENCES roles(id) -- Assuming roles is the name of the Role table
);