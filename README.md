# panic buying

---

> 抢购项目，暂支持单机环境

#### 技术点

1. Jdk1.8
2. H2
3. SpringBoot
5. Queue
6. Cache
7. Lock
8. MultiThread
9. FastJson
10. Jpa

#### 数据库地址 [http://localhost:8080/h2](http://localhost:8080/h2)
 
> 用户:root,密码:123456

---

### check list

todo | todo_date | todo_user | done | done_date | done_user
---|---|---|---|---|---
本地环境 cache、h2、queue，添加另一组环境，用redis、mysql、mq | 2020/3/6 | whvixd | - | - | -
消费者用socket方式进行消费 | 2020/3/6 | whvixd | - | - | -
添加配置文件，不需要本地写死 | 2020/3/6 | whvixd | Yes | 2020/8/6 | whvixd
优化异常 | 2020/3/6 | whvixd | Yes | 2020/7/1 | whvixd 
完善测试 | 2020/3/7 | whvixd| Yes | 2020/7/1 | whvixd
订单进行分表 | 2020/7/1 | whvixd | Yes | 2020/7/1 | whvixd
实现通过注解添加 分布式锁(lua)，请求头添加userId | 2020/7/29 | whvixd | - | - | -
优化表结构 | 2020/8/5 | whvixd | - | - | - 
添加入参参数校验 | 2020/8/5 | whvixd | Yes | 2020/8/7 | whvixd 
添加异常处理类 | 2020/8/5 | whvixd | Yes | 2020/8/5 | whvixd 
代码逻辑修改(大改造) | 2020/8/7 | whvixd | doing | - | -
产品表添加CRUD，订单提供查询和删除接口(逻辑删) | 2020/8/5 | whvixd | - | - | -
mock 支付接口，https调用，添加支付日志表 | 2020/8/5 | whvixd | - | - | -
添加 log4j2.xml，记录调用traceId | 2020/8/5 | whvixd | - | - | -

### log

1. 2020/8/6 修改product/sale_order_x 表结构

> h2 没有 comment

2. 2020/8/7 添加常量及工具类