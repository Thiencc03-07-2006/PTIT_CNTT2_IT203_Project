DROP DATABASE smartphone_store_management;
CREATE DATABASE smartphone_store_management;
USE smartphone_store_management;
CREATE TABLE users
(
    user_id      INT PRIMARY KEY AUTO_INCREMENT,
    user_name    VARCHAR(100) NOT NULL UNIQUE,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    address      TEXT,

    gender       ENUM ('MALE','FEMALE','OTHER') default 'OTHER',
    birthday     DATE         NULL,

    role         ENUM ('ADMIN','CUSTOMER')      DEFAULT 'CUSTOMER',
    status       TINYINT(1)                     DEFAULT 1,

    created_date TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE categories
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    status     BOOLEAN   DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE products
(
    product_id   INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    root_price   DOUBLE       NOT NULL,
    discount     INT                                       DEFAULT 0,
    inventory    INT                                       DEFAULT 0,
    color        VARCHAR(50),
    description  TEXT,
    cate_id      INT,
    status       ENUM ('ACTIVE','INACTIVE','OUT_OF_STOCK') DEFAULT 'ACTIVE',
    brand        VARCHAR(100),
    storage      VARCHAR(50),
    created_date DATETIME                                  DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_product_name ON products (product_name);
CREATE TABLE orders
(
    order_id      INT PRIMARY KEY AUTO_INCREMENT,
    customer_id   INT,
    customer_name VARCHAR(100),
    phone_number  VARCHAR(20),
    email         VARCHAR(100),
    address       TEXT,
    total_money   DOUBLE,
    status        ENUM ('PENDING','SHIPPING','DELIVERED','CANCELLED') DEFAULT 'PENDING',
    created_date  DATETIME                                            DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES users (user_id)
);
CREATE TABLE order_details
(
    order_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id        INT    NOT NULL,
    product_id      INT,

    product_name    VARCHAR(255),
    price           DOUBLE NOT NULL,
    quantity        INT    NOT NULL,
    total_money     DOUBLE,

    color           VARCHAR(50),
    storage         VARCHAR(50),

    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);
CREATE TABLE flash_sales
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    product_id       INT,
    discount_percent INT,
    quantity_limit   INT,
    start_time       DATETIME,
    end_time         DATETIME,

    FOREIGN KEY (product_id) REFERENCES products (product_id)
);
CREATE TABLE coupons
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    code             VARCHAR(50) UNIQUE,
    discount_percent INT,
    expire_date      DATE,
    status           BOOLEAN DEFAULT TRUE
);
ALTER TABLE categories
    ADD CONSTRAINT UC_Category_Name UNIQUE (name);

ALTER TABLE categories
    MODIFY name VARCHAR(100)
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_0900_as_ci;
