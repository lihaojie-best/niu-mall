
Create Table

    CREATE TABLE `product` (
    `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性id',
    `product_name` varchar(30) DEFAULT NULL COMMENT '属性名',
    `product_price` int(10) NOT NULL COMMENT '价格',
    `product_sales_volume` int(10) NOT NULL COMMENT '销售量  ',
    PRIMARY KEY (`product_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 COMMENT='商品基本信息'
