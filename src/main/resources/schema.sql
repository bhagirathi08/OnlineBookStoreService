CREATE TABLE Book (
    id bigint AUTOINCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    isbn varchar(255) NOT NULL UNIQUE,
    price float NOT NULL,
    quantity int
);