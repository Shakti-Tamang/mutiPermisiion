
CREATE TABLE IF NOT EXISTS add_book (
    id VARCHAR(255) PRIMARY KEY,
    book_title VARCHAR(255) NOT NULL,
    book_category VARCHAR(255) NOT NULL,
    boot_quantity INTEGER,  -- Nullable, as per the entity definition
    availability VARCHAR(255) NOT NULL,
    user_id VARCHAR(255),  -- Foreign key reference to UserModel

    FOREIGN KEY (user_id) REFERENCES users(id)  -- Establishing the relationship with UserModel
);



