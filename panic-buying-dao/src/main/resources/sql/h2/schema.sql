-- 产品
DROP TABLE IF EXISTS product;
CREATE TABLE product (
  id          BIGINT                                             NOT NULL PRIMARY KEY AUTO_INCREMENT
  COMMENT '主键',
  product_id  VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '产品id',
  name        VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '产品名称',
  total       INT DEFAULT 0                                      NOT NULL
  COMMENT '总数',
  sold_number INT DEFAULT 0                                      NOT NULL
  COMMENT '销售数量',
  price       BIGINT DEFAULT 0                                   NOT NULL
  COMMENT '价格 单位:分',
  info        VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '产品信息',
  delete      INT DEFAULT 0                                      NOT NULL
  COMMENT '是否删除，0-未删除，1-已删除',
  stauts      INT DEFAULT 0                                      NOT NULL
  COMMENT '状态，0-下架，1-上架',
  current     INT DEFAULT 0                                      NOT NULL
  COMMENT '是否当前版本，0-非 1-当前版本',
  remark      VARCHAR(1000) DEFAULT ''                           NOT NULL
  COMMENT '额外字段',
  version     BIGINT DEFAULT 0                                   NOT NULL
  COMMENT '版本号',
  create_user VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '创建人',
  create_time DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '创建时间',
  update_user VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '更新人',
  update_time DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '更新时间',
  CONSTRAINT uniq_pid_version UNIQUE (product_id, version)
);

-- 分表:根据PRODUCT_ID进行分表，哈希PRODUCT_ID

-- 订单
DROP TABLE IF EXISTS sale_order_0;
CREATE TABLE sale_order_0 (
  id             BIGINT                                             NOT NULL PRIMARY KEY AUTO_INCREMENT
  COMMENT '主键',
  order_id       VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '订单id',
  name           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单名称',
  product_id     VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '产品id',
  pay_amount     BIGINT DEFAULT 0                                   NOT NULL
  COMMENT '支付金额 单位:分',
  delete         INT DEFAULT 0                                      NOT NULL
  COMMENT '是否删除，0-未删除，1-已删除',
  info           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单信息',
  user_id        VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '购买者',
  remark         VARCHAR(1000) DEFAULT ''                           NOT NULL
  COMMENT '额外字段',
  total_duration INT DEFAULT 1                                      NOT NULL
  COMMENT '还款期数',
  create_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '创建人',
  create_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '创建时间',
  update_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '更新人',
  update_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '更新时间',
  CONSTRAINT uniq_oid UNIQUE (order_id)
);

-- 订单
DROP TABLE IF EXISTS sale_order_1;
CREATE TABLE sale_order_1 (
  id             BIGINT                                             NOT NULL PRIMARY KEY AUTO_INCREMENT
  COMMENT '主键',
  order_id       VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '订单id',
  name           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单名称',
  product_id     VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '产品id',
  pay_amount     BIGINT DEFAULT 0                                   NOT NULL
  COMMENT '支付金额 单位:分',
  delete         INT DEFAULT 0                                      NOT NULL
  COMMENT '是否删除，0-未删除，1-已删除',
  info           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单信息',
  user_id        VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '购买者',
  remark         VARCHAR(1000) DEFAULT ''                           NOT NULL
  COMMENT '额外字段',
  total_duration INT DEFAULT 1                                      NOT NULL
  COMMENT '还款期数',
  create_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '创建人',
  create_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '创建时间',
  update_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '更新人',
  update_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '更新时间',
  CONSTRAINT uniq_oid UNIQUE (order_id)
);

-- 订单
DROP TABLE IF EXISTS sale_order_2;
CREATE TABLE sale_order_2 (
  id             BIGINT                                             NOT NULL PRIMARY KEY AUTO_INCREMENT
  COMMENT '主键',
  order_id       VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '订单id',
  name           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单名称',
  product_id     VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '产品id',
  pay_amount     BIGINT DEFAULT 0                                   NOT NULL
  COMMENT '支付金额 单位:分',
  delete         INT DEFAULT 0                                      NOT NULL
  COMMENT '是否删除，0-未删除，1-已删除',
  info           VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '订单信息',
  user_id        VARCHAR(64) DEFAULT ''                             NOT NULL
  COMMENT '购买者',
  remark         VARCHAR(1000) DEFAULT ''                           NOT NULL
  COMMENT '额外字段',
  total_duration INT DEFAULT 1                                      NOT NULL
  COMMENT '还款期数',
  create_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '创建人',
  create_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '创建时间',
  update_user    VARCHAR(100) DEFAULT ''                            NOT NULL
  COMMENT '更新人',
  update_time    DATETIME DEFAULT '1971-01-01 00:00:00'             NOT NULL
  COMMENT '更新时间',
  CONSTRAINT uniq_oid UNIQUE (order_id)
);
