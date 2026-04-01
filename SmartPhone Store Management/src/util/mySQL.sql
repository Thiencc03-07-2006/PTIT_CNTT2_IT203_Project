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
    name       VARCHAR(100) NOT NULL UNIQUE,
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
    created_date TIMESTAMP                                 DEFAULT CURRENT_TIMESTAMP
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
    created_date  TIMESTAMP                                           DEFAULT CURRENT_TIMESTAMP,
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
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);
CREATE TABLE coupons
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    code             VARCHAR(50) UNIQUE,
    discount_percent INT,
    expire_date      DATE,
    status           BOOLEAN   DEFAULT TRUE,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart
(
    cart_id      INT AUTO_INCREMENT PRIMARY KEY,
    customer_id  INT NOT NULL,
    product_id   INT NOT NULL,
    quantity     INT NOT NULL DEFAULT 1,
    created_date TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (customer_id) REFERENCES users (user_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

USE smartphone_store_management;

-- 1. THÊM DANH MỤC (CATEGORIES)
INSERT INTO categories (name, status)
VALUES ('iPhone', 1),
       ('Samsung Galaxy', 1),
       ('Xiaomi & Redmi', 1),
       ('Oppo & RealMe', 1),
       ('Phụ kiện Apple', 1),
       ('Phụ kiện Android', 1),
       ('Máy tính bảng (Tablet)', 1),
       ('Đồng hồ thông minh', 1),
       ('Laptop', 1),
       ('Loa & Tai nghe', 1);

-- 2. THÊM SẢN PHẨM (PRODUCTS) - Đủ các thương hiệu để test tìm kiếm

-- CHÈN LẠI DỮ LIỆU VỚI MÀU SẮC CHUẨN TRONG DANH SÁCH JAVA
INSERT INTO products (product_name, root_price, discount, inventory, color, description, cate_id, status, brand,
                      storage)
VALUES ('iPhone 15 Pro Max', 32000000, 5, 25, 'Black', 'Chip A17 Pro mạnh mẽ', 1, 'ACTIVE', 'Apple', '256GB'),
       ('iPhone 15 Plus', 25000000, 0, 15, 'Blue', 'Màn hình lớn, pin trâu', 1, 'ACTIVE', 'Apple', '128GB'),
       ('iPhone 13', 14500000, 10, 50, 'Black', 'Giá rẻ cấu hình tốt', 1, 'ACTIVE', 'Apple', '128GB'),
       ('Samsung Galaxy S24 Ultra', 29000000, 7, 20, 'Black', 'AI Phone thế hệ mới', 2, 'ACTIVE', 'Samsung', '512GB'),
       ('Samsung Galaxy Z Fold 5', 35000000, 15, 10, 'Blue', 'Màn hình gập đỉnh cao', 2, 'ACTIVE', 'Samsung', '256GB'),
       ('Samsung Galaxy A54', 8500000, 5, 0, 'Green', 'Tầm trung bán chạy', 2, 'OUT_OF_STOCK', 'Samsung', '128GB'),
       ('Xiaomi 14 Ultra', 26000000, 5, 12, 'White', 'Ống kính Leica chuyên nghiệp', 3, 'ACTIVE', 'Xiaomi', '512GB'),
       ('Redmi Note 13 Pro', 7500000, 0, 40, 'Blue', 'Camera 200MP siêu nét', 3, 'ACTIVE', 'Xiaomi', '256GB'),
       ('Oppo Reno11 Pro', 14000000, 5, 15, 'White', 'Chuyên gia chụp ảnh chân dung', 4, 'ACTIVE', 'Oppo', '256GB'),
       ('Realme GT5', 11000000, 2, 8, 'White', 'Sạc siêu nhanh 240W', 4, 'ACTIVE', 'Realme', '256GB'),
       ('iPad Pro M2', 21000000, 0, 10, 'Black', 'Màn hình Liquid Retina mượt mà', 7, 'ACTIVE', 'Apple', '128GB'),
       ('iPad Air 5', 14000000, 5, 15, 'Purple', 'Chip M1 hiệu năng cao', 7, 'ACTIVE', 'Apple', '64GB'),
       ('Apple Watch Series 9', 9500000, 5, 30, 'Red', 'Theo dõi sức khỏe thông minh', 8, 'ACTIVE', 'Apple', '41mm'),
       ('Samsung Galaxy Watch 6', 6000000, 10, 25, 'Black', 'Hệ điều hành WearOS mượt', 8, 'ACTIVE', 'Samsung', '44mm'),
       ('AirPods Pro Gen 2', 5500000, 5, 100, 'White', 'Chống ồn chủ động ANC', 10, 'ACTIVE', 'Apple', 'N/A'),
       ('Sony WH-1000XM5', 7000000, 8, 20, 'Black', 'Tai nghe chống ồn số 1', 10, 'ACTIVE', 'Sony', 'N/A'),
       ('MacBook Air M2', 24000000, 5, 10, 'Yellow', 'Thiết kế mỏng nhẹ tinh tế', 9, 'ACTIVE', 'Apple', '256GB'),
       ('Asus ROG Strix', 30000000, 0, 5, 'Black', 'Laptop Gaming chuyên nghiệp', 9, 'ACTIVE', 'Asus', '512GB'),
       ('Ốp lưng MagSafe iPhone 15', 1200000, 0, 200, 'White', 'Phụ kiện chính hãng Apple', 5, 'ACTIVE', 'Apple',
        'N/A'),
       ('Sạc nhanh Samsung 45W', 500000, 0, 150, 'Black', 'Sạc siêu nhanh chuẩn 2.0', 6, 'ACTIVE', 'Samsung', 'N/A');

-- 3. THÊM NGƯỜI DÙNG (USERS)
INSERT INTO users (user_name, email, password, phone_number, address, gender, birthday, role, status)
VALUES ('user1', 'user1@gmail.com', '$2a$12$O87A9Nr9xYkJeHJD3103Ze3Pw/3mszntemlvyRt8uNnGLrTSWxQ1O', '0911223344', 'Hà Nội', 'MALE', '1995-01-10',
        'CUSTOMER', 1);
#        ('khach_hang_2', 'user2@gmail.com', 'hash_password_here', '0944555666', 'Đà Nẵng', 'FEMALE', '1997-05-15',
#         'CUSTOMER', 1),
#        ('khach_hang_3', 'user3@gmail.com', 'hash_password_here', '0977888999', 'TP HCM', 'MALE', '1990-12-25',
#         'CUSTOMER', 1),
#        ('khach_hang_4', 'user4@gmail.com', 'hash_password_here', '0900111222', 'Cần Thơ', 'FEMALE', '2000-08-08',
#         'CUSTOMER', 1);

-- 4. THÊM ĐƠN HÀNG (ORDERS) - Để test thống kê doanh thu
-- Đơn hàng 1: Đã giao
# INSERT INTO orders (customer_id, customer_name, phone_number, email, address, total_money, status, created_date)
# VALUES (2, 'Khách hàng 1', '0911222333', 'user1@gmail.com', 'Hà Nội', 46500000, 'DELIVERED', '2026-03-10 10:00:00'),
#        (3, 'Khách hàng 2', '0944555666', 'user2@gmail.com', 'Đà Nẵng', 14500000, 'DELIVERED', '2026-03-15 14:20:00'),
#        (4, 'Khách hàng 3', '0977888999', 'user3@gmail.com', 'TP HCM', 5500000, 'SHIPPING', '2026-03-25 09:15:00'),
#        (5, 'Khách hàng 4', '0900111222', 'user4@gmail.com', 'Cần Thơ', 32000000, 'PENDING', '2026-03-31 20:45:00'),
#        (2, 'Khách hàng 1', '0911222333', 'user1@gmail.com', 'Hà Nội', 7500000, 'CANCELLED', '2026-03-12 11:00:00');

# -- 5. CHI TIẾT ĐƠN HÀNG (ORDER_DETAILS) - Để test Top sản phẩm bán chạy
# INSERT INTO order_details (order_id, product_id, product_name, price, quantity, total_money, color, storage)
# VALUES (1, 1, 'iPhone 15 Pro Max', 30400000, 1, 30400000, 'Natural Titan', '256GB'),
#        (1, 12, 'iPad Air 5', 13300000, 1, 13300000, 'Purple', '64GB'),
#        (1, 19, 'Ốp lưng MagSafe iPhone 15', 1200000, 2, 2400000, 'Clear', 'N/A'),
#        (2, 3, 'iPhone 13', 14500000, 1, 14500000, 'Midnight', '128GB'),
#        (3, 15, 'AirPods Pro Gen 2', 5500000, 1, 5500000, 'White', 'N/A'),
#        (4, 1, 'iPhone 15 Pro Max', 32000000, 1, 32000000, 'Natural Titan', '256GB'),
#        (5, 8, 'Redmi Note 13 Pro', 7500000, 1, 7500000, 'Ocean Blue', '256GB');

-- 6. MÃ GIẢM GIÁ (COUPONS)
INSERT INTO coupons (code, discount_percent, expire_date, status)
VALUES ('GiamGiaHe', 15, '2026-08-31', 1),
       ('TET2026', 20, '2026-02-15', 0),
       ('FREESHIP', 5, '2026-12-31', 1),
       ('DANGKHOA', 10, '2026-06-30', 1);

-- 7. FLASH SALE
INSERT INTO flash_sales (product_id, discount_percent, quantity_limit, start_time, end_time)
VALUES (1, 10, 5, '2026-04-01 12:00:00', '2026-04-01 14:00:00'),
       (4, 20, 3, '2026-04-01 20:00:00', '2026-04-01 22:00:00'),
       (15, 50, 10, '2026-04-05 00:00:00', '2026-04-05 23:59:59');