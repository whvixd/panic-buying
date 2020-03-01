-- 产品
create table if not exists PRODUCT (
PRODUCT_ID int not null primary key auto_increment,
PRODUCT_NAME varchar(100) not null,
PRODUCT_TOTAL int not null,
PRODUCT_SOLD int not null,
PRODUCT_VERSION int);

-- 订单
create table if not exists SALE_ORDER (
ORDER_ID int not null primary key auto_increment,
PRODUCT_ID int not null,
ORDER_NAME varchar(100) not null,
CREATE_TIME timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
