# Host: localhost  (Version 5.7.27-log)
# Date: 2019-11-08 23:49:13
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "order_detail"
#

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "order_detail"
#

INSERT INTO `order_detail` VALUES ('1573227449902312991','1573227449562543494','1','徐睿',668.68,1,NULL,'2019-11-08 22:43:52','2019-11-08 22:43:52'),('1573227449932438933','1573227449562543494','2','范冰冰',568.89,2,NULL,'2019-11-08 22:45:39','2019-11-08 22:45:39'),('1573227579754262356','1573227579392274798','1','徐睿',668.68,1,NULL,'2019-11-08 22:43:52','2019-11-08 22:43:52'),('1573227579780654563','1573227579392274798','2','范冰冰',568.89,2,NULL,'2019-11-08 22:45:39','2019-11-08 22:45:39'),('1573227951915309764','1573227951619768099','1','徐睿',668.68,3,NULL,'2019-11-08 22:43:52','2019-11-08 22:43:52'),('1573227951939137221','1573227951619768099','2','范冰冰',568.89,4,NULL,'2019-11-08 22:45:39','2019-11-08 22:45:39');

#
# Structure for table "order_master"
#

DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "order_master"
#

INSERT INTO `order_master` VALUES ('1573227449562543494','梁晟','123456789012','工业学院','110119',1806.46,0,0,'2019-11-08 23:37:29','2019-11-08 23:37:29'),('1573227579392274798','梁晟','123456789012','工业学院','110119',1806.46,0,0,'2019-11-08 23:39:39','2019-11-08 23:39:39'),('1573227951619768099','梁晟','123456789012','工业学院','110119',4281.60,0,0,'2019-11-08 23:45:51','2019-11-08 23:45:51');

#
# Structure for table "product_category"
#

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "product_category"
#

INSERT INTO `product_category` VALUES (1,'爱宠',1,'2019-11-08 22:44:24','2019-11-08 22:44:24'),(2,'玩物',2,'2019-11-08 22:44:53','2019-11-08 22:44:53');

#
# Structure for table "product_info"
#

DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "product_info"
#

INSERT INTO `product_info` VALUES ('1','徐睿',668.68,5,NULL,NULL,0,1,'2019-11-08 22:43:52','2019-11-08 22:43:52'),('2','范冰冰',568.89,12,NULL,NULL,0,2,'2019-11-08 22:45:39','2019-11-08 22:45:39');

#
# Structure for table "seller_info"
#

DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

#
# Data for table "seller_info"
#

