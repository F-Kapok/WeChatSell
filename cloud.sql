/*
Navicat MySQL Data Transfer

Source Server         : 192.168.242.130
Source Server Version : 50723
Source Host           : 192.168.242.130:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-16 17:56:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `order_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `product_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `product_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `buyer_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_master
-- ----------------------------

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热榜', '1', '2017-03-28 16:40:22', '2018-12-26 11:48:02');
INSERT INTO `product_category` VALUES ('2', '好吃的', '2', '2017-03-14 17:38:46', '2018-12-26 11:48:11');
INSERT INTO `product_category` VALUES ('3', '男生最爱', '3', '2019-01-02 16:51:23', '2019-01-02 23:32:39');
INSERT INTO `product_category` VALUES ('4', '女生最爱', '4', '2019-01-15 13:03:38', '2019-01-15 13:03:50');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `product_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1546421049882', '烤翅', '12.50', '500', '香嫩的烤鸡翅', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '2', '2019-01-02 17:24:10', '2019-01-16 14:29:17');
INSERT INTO `product_info` VALUES ('1547519951036346', '最爱皮皮虾', '0.22', '2000', '鲜嫩', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '3', '2019-01-15 10:39:11', '2019-01-15 11:00:06');
INSERT INTO `product_info` VALUES ('1547620377942985', '巨无霸', '258.00', '1000', '鲜嫩，肉多', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '4', '2019-01-16 14:32:59', '2019-01-16 14:32:59');
INSERT INTO `product_info` VALUES ('157875196366160022', '皮蛋粥', '0.01', '319', '好吃的皮蛋粥', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '0', '1', '2017-03-28 19:39:15', '2019-01-17 00:17:08');
INSERT INTO `product_info` VALUES ('157875227953464068', '慕斯蛋糕', '10.90', '200', '美味爽口', '//fuss10.elemecdn.com/9/93/91994e8456818dfe7b0bd95f10a50jpeg.jpeg', '0', '1', '2017-03-28 19:35:54', '2019-01-14 15:30:56');
INSERT INTO `product_info` VALUES ('164103465734242707', '蜜汁鸡翅', '0.02', '483', '好吃', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '1', '2017-03-30 17:11:56', '2019-01-16 16:41:36');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(32) COLLATE utf8_unicode_ci DEFAULT '',
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT '',
  `openid` varchar(64) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '微信openid',
  `role` tinyint(1) NOT NULL COMMENT '0买家1卖家',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', 'admin', 'asd', '0', '2018-12-30 12:24:57', '2019-01-15 22:39:06');
INSERT INTO `user_info` VALUES ('2', 'kapok', 'kapok', 'zxc', '1', '2018-12-30 12:25:10', '2019-01-15 22:39:08');
