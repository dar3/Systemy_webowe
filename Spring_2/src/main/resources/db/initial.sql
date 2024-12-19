CREATE SCHEMA IF NOT EXISTS sklep;

CREATE TABLE IF NOT EXISTS sklep.category
(
    id integer primary key,
    category_name varchar(50) not null unique,
    code varchar(50) not null unique
);

CREATE TABLE IF NOT EXISTS sklep.product
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL unique,
    weight DOUBLE,
    price DOUBLE,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES sklep.category (id)
);

CREATE TABLE IF NOT EXISTS sklep.users
(
    id INTEGER PRIMARY KEY ,
    username VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    role ENUM('ADMIN', 'USER') NOT NULL
);