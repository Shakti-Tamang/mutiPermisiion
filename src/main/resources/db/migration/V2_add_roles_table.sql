-- Migration script to create roles table

-- Create the roles table
CREATE TABLE IF NOT EXISTS roles (
    id VARCHAR(255) PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

-- Optional: Insert initial roles into the roles table
INSERT INTO roles (id, role_name) VALUES
('1', 'ADMIN'),
('2', 'STUDENT'),
('3', 'TEACHER'),
('4', 'LIBRARIAN')
ON CONFLICT (id) DO NOTHING;  -- Avoid duplicate entries

