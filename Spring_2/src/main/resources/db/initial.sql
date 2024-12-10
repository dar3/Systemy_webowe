CREATE SCHEMA IF NOT EXISTS sklep;

CREATE TABLE IF NOT EXISTS sklep.category(
    id           integer  primary key,
    category_name     varchar(50)   not null,
    code    varchar(50)   not null
    );

CREATE TABLE IF NOT EXISTS sklep.product
(
    id INTEGER PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    weight DOUBLE,
    price DOUBLE,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES sklep.category (id)
);

