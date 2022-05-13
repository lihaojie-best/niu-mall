CREATE TABLE `product_details` (
                                   `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性id',
                                   `product_company` varchar(30) DEFAULT NULL COMMENT '生产公司',
                                   `product_date` date NOT NULL COMMENT '日期',
                                   PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COMMENT='商品详情'
