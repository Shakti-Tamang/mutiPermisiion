-- Create the fine table
CREATE TABLE fine (
    id VARCHAR(255) PRIMARY KEY,
    fine SMALLINT NOT NULL,
    user_fine_id VARCHAR(255) UNIQUE,
    FOREIGN KEY (user_fine_id) REFERENCES users(id) ON DELETE CASCADE
);