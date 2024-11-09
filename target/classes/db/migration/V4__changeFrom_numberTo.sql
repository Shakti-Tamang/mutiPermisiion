-- Migration script to rename boot_quantity to number_of_book in add_book table

-- Rename boot_quantity to number_of_book
ALTER TABLE add_book
RENAME COLUMN boot_quantity TO number_of_book;