CREATE TABLE book_checkout (
    id VARCHAR(255) PRIMARY KEY,
    checkout_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    users_id VARCHAR(255),
    add_book_id VARCHAR(255),
    FOREIGN KEY (users_id) REFERENCES users(id), -- Many-to-one relationship with UserModel
    FOREIGN KEY (add_book_id) REFERENCES add_book(id) -- Many-to-one relationship with AddBook
);