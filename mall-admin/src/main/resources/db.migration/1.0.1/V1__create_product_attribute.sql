CREATE TABLE `product_attribute` (
                                     `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性id',
                                     `product_colour` varchar(30) DEFAULT NULL COMMENT '颜色',
                                     `product_type` varchar(10) NOT NULL COMMENT '型号',
                                     `product_stock` int(10) NOT NULL COMMENT '库存',
                                     PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COMMENT='商品属性信息'