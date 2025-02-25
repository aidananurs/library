-- Insert into authors table
INSERT INTO library_schema.authors (author_id, name, nationality, birth_date) VALUES
(1, 'Jane Austen', 'British', '1775-12-16'),
(2, 'F. Scott Fitzgerald', 'American', '1896-09-24'),
(3, 'George Orwell', 'British', '1903-06-25'),
(4, 'Mark Twain', 'American', '1835-11-30'),
(5, 'J.K. Rowling', 'British', '1965-07-31'),
(6, 'Ernest Hemingway', 'American', '1899-07-21'),
(7, 'Leo Tolstoy', 'Russian', '1828-09-09'),
(8, 'Harper Lee', 'American', '1926-04-28'),
(9, 'J.D. Salinger', 'American', '1919-01-01'),
(10, 'J.R.R. Tolkien', 'British', '1892-01-03'),
(11, 'Herman Melville', 'American', '1819-08-01'),
(12, 'Homer', 'Greek', '1800-01-01');

-- Insert into categories table
INSERT INTO library_schema.categories (category_id, category_name) VALUES
(1, 'Romance'),
(2, 'Fiction'),
(3, 'Dystopian'),
(4, 'Adventure'),
(5, 'Fantasy'),
(6, 'Literary Fiction'),
(7, 'Historical Fiction');

-- Insert into books table
INSERT INTO library_schema.books (book_id, serial_number, title, category_id, publisher, year_published, location, copies_available) VALUES
(1,'SN001', 'Pride and Prejudice', 1, 'Penguin Books', 1813, 'A1', 5),
(2,'SN002', 'The Great Gatsby', 2, 'Scribner', 1925, 'B2', 3),
(3,'SN003', '1984', 3, 'Secker & Warburg', 1949, 'C3', 4),
(4,'SN004', 'Adventures of Huckleberry Finn', 4, 'Chatto & Windus', 1884, 'D4', 2),
(5,'SN005', 'Harry Potter and the Philosopher Stone', 5, 'Bloomsbury', 1997, 'E5', 6),
(6,'SN006', 'The Old Man and the Sea', 6, 'Charles Scribner Sons', 1952, 'F6', 7),
(7,'SN007', 'War and Peace', 7, 'The Russian Messenger', 1869, 'G7', 1),
(8,'SN008', 'To Kill a Mockingbird', 2, 'J.B. Lippincott & Co.', 1960, 'H8', 5),
(9,'SN009', 'The Catcher in the Rye', 2, 'Little, Brown and Company', 1951, 'I9', 4),
(10,'SN010', 'The Lord of the Rings', 5, 'Allen & Unwin', 1954, 'J10', 3),
(11,'SN011', 'Moby-Dick', 6, 'Harper & Brothers', 1851, 'K11', 2),
(12,'SN012', 'The Odyssey', 7, 'Ancient Greece', -800, 'L12', 1);

-- Insert into members table
INSERT INTO library_schema.members (member_id, name, phone_number, email) VALUES
(1, 'John Doe', '+1234567890', 'johndoe@example.com'),
(2, 'Jane Smith', '+1234554321', 'janesmith@example.com'),
(3, 'Alice Johnson', '+1234561234', 'alicejohnson@example.com'),
(4, 'Bob Brown', '+1234564321', 'bobbrown@example.com'),
(5, 'Charlie Davis', '+1234565678', 'charliedavis@example.com'),
(6, 'Eve Adams', '+1234568765', 'eveadams@example.com'),
(7, 'Frank White', '+1234563456', 'frankwhite@example.com');

-- Insert into borrowings table
INSERT INTO library_schema.borrowings (borrowing_id, book_id, member_id, borrow_date, return_date) VALUES
(1, 1, 1, '2025-02-25', '2025-03-10'),
(2, 2, 2, '2025-02-26', '2025-03-15'),
(3, 3, 3, '2025-02-27', '2025-03-12'),
(4, 4, 4, '2025-02-28', '2025-03-14'),
(5, 5, 5, '2025-03-01', '2025-03-16'),
(6, 6, 6, '2025-03-02', '2025-03-17'),
(7, 7, 7, '2025-03-03', '2025-03-18');

-- Insert into book_authors joint table
INSERT INTO library_schema.book_authors (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12);