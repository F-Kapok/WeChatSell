DROP DATABASE
IF EXISTS cloud;
CREATE DATABASE cloud CHARACTER
SET utf8 COLLATE utf8_Unicode_ci;
USE cloud;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.242.130
Source Server Version : 50723
Source Host           : 192.168.242.130:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-04 17:44:59
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
INSERT INTO `order_detail` VALUES ('1545873946848463', '1545873939643625', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2017-03-28 19:39:15', '2017-07-02 11:45:44');
INSERT INTO `order_detail` VALUES ('1545873947287377', '1545873939643625', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2017-03-30 17:11:56', '2017-06-24 19:20:54');
INSERT INTO `order_detail` VALUES ('1545887331565300', '1545887330679120', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-27 13:08:51', '2018-12-27 13:08:51');
INSERT INTO `order_detail` VALUES ('1545887331808446', '1545887330679120', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-27 13:08:51', '2018-12-27 13:08:51');
INSERT INTO `order_detail` VALUES ('1545887862599240', '1545887862210445', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-27 13:17:42', '2018-12-27 13:17:42');
INSERT INTO `order_detail` VALUES ('1545887862722276', '1545887862210445', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-27 13:17:42', '2018-12-27 13:17:42');
INSERT INTO `order_detail` VALUES ('1545887881040701', '1545887881030192', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-27 13:18:01', '2018-12-27 13:18:01');
INSERT INTO `order_detail` VALUES ('1545887881079739', '1545887881030192', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-27 13:18:01', '2018-12-27 13:18:01');
INSERT INTO `order_detail` VALUES ('1545887905332837', '1545887905321953', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-27 13:18:25', '2018-12-27 13:18:25');
INSERT INTO `order_detail` VALUES ('1545887905380734', '1545887905321953', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-27 13:18:25', '2018-12-27 13:18:25');
INSERT INTO `order_detail` VALUES ('1546228722894113', '1546228699741837', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-31 11:58:45', '2018-12-31 11:58:45');
INSERT INTO `order_detail` VALUES ('1546228724752874', '1546228699741837', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-31 11:58:45', '2018-12-31 11:58:45');
INSERT INTO `order_detail` VALUES ('1546228763055308', '1546228763018399', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-31 11:59:23', '2018-12-31 11:59:23');
INSERT INTO `order_detail` VALUES ('1546228763068740', '1546228763018399', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-31 11:59:23', '2018-12-31 11:59:23');
INSERT INTO `order_detail` VALUES ('1546228799688412', '1546228799655961', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-31 12:00:00', '2018-12-31 12:00:00');
INSERT INTO `order_detail` VALUES ('1546228799702532', '1546228799655961', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-31 12:00:00', '2018-12-31 12:00:00');
INSERT INTO `order_detail` VALUES ('1546228815540683', '1546228815492518', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-31 12:00:16', '2018-12-31 12:00:16');
INSERT INTO `order_detail` VALUES ('1546231321996681', '1546231321966898', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-31 12:42:02', '2018-12-31 12:42:02');
INSERT INTO `order_detail` VALUES ('1546231322008398', '1546231321966898', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-31 12:42:02', '2018-12-31 12:42:02');
INSERT INTO `order_detail` VALUES ('1546332469922894', '1546332469321497', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:47:51', '2019-01-01 16:47:51');
INSERT INTO `order_detail` VALUES ('1546332470535287', '1546332469321497', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:47:51', '2019-01-01 16:47:51');
INSERT INTO `order_detail` VALUES ('1546332855230106', '1546332854715952', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:54:15', '2019-01-01 16:54:15');
INSERT INTO `order_detail` VALUES ('1546332855318281', '1546332854715952', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:54:15', '2019-01-01 16:54:15');
INSERT INTO `order_detail` VALUES ('1546332859766522', '1546332859730269', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:54:19', '2019-01-01 16:54:19');
INSERT INTO `order_detail` VALUES ('1546332859780581', '1546332859730269', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:54:19', '2019-01-01 16:54:19');
INSERT INTO `order_detail` VALUES ('1546332866582591', '1546332866541711', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:54:26', '2019-01-01 16:54:26');
INSERT INTO `order_detail` VALUES ('1546332866597611', '1546332866541711', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:54:26', '2019-01-01 16:54:26');
INSERT INTO `order_detail` VALUES ('1546333016442330', '1546333015974815', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:56:56', '2019-01-01 16:56:56');
INSERT INTO `order_detail` VALUES ('1546333016516774', '1546333015974815', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:56:56', '2019-01-01 16:56:56');
INSERT INTO `order_detail` VALUES ('1546333021124241', '1546333021095692', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:57:01', '2019-01-01 16:57:01');
INSERT INTO `order_detail` VALUES ('1546333021135197', '1546333021095692', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:57:01', '2019-01-01 16:57:01');
INSERT INTO `order_detail` VALUES ('1546333038450599', '1546333038423855', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:57:18', '2019-01-01 16:57:18');
INSERT INTO `order_detail` VALUES ('1546333038461937', '1546333038423855', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:57:18', '2019-01-01 16:57:18');
INSERT INTO `order_detail` VALUES ('1546333165251520', '1546333164724958', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 16:59:25', '2019-01-01 16:59:25');
INSERT INTO `order_detail` VALUES ('1546333165331802', '1546333164724958', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 16:59:25', '2019-01-01 16:59:25');
INSERT INTO `order_detail` VALUES ('1546333202885489', '1546333202852321', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:00:02', '2019-01-01 17:00:02');
INSERT INTO `order_detail` VALUES ('1546333202910301', '1546333202852321', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:00:02', '2019-01-01 17:00:02');
INSERT INTO `order_detail` VALUES ('1546333228637886', '1546333228594648', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:00:28', '2019-01-01 17:00:28');
INSERT INTO `order_detail` VALUES ('1546333228650189', '1546333228594648', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:00:28', '2019-01-01 17:00:28');
INSERT INTO `order_detail` VALUES ('1546333250431968', '1546333250402340', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:00:50', '2019-01-01 17:00:50');
INSERT INTO `order_detail` VALUES ('1546333250444058', '1546333250402340', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:00:50', '2019-01-01 17:00:50');
INSERT INTO `order_detail` VALUES ('1546333495058934', '1546333495010896', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:04:55', '2019-01-01 17:04:55');
INSERT INTO `order_detail` VALUES ('1546333495072986', '1546333495010896', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:04:55', '2019-01-01 17:04:55');
INSERT INTO `order_detail` VALUES ('1546333667037364', '1546333664552113', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:07:47', '2019-01-01 17:07:47');
INSERT INTO `order_detail` VALUES ('1546333667114251', '1546333664552113', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:07:47', '2019-01-01 17:07:47');
INSERT INTO `order_detail` VALUES ('1546333800403497', '1546333797862827', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:10:00', '2019-01-01 17:10:00');
INSERT INTO `order_detail` VALUES ('1546333800474417', '1546333797862827', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:10:00', '2019-01-01 17:10:00');
INSERT INTO `order_detail` VALUES ('1546333853293691', '1546333851246519', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-01 17:10:53', '2019-01-01 17:10:53');
INSERT INTO `order_detail` VALUES ('1546333853311507', '1546333851246519', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-01 17:10:53', '2019-01-01 17:10:53');
INSERT INTO `order_detail` VALUES ('154650419822381', '154650419808903', '1546421049882', '烤翅', '11.50', '1', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-03 16:29:58', '2019-01-03 16:29:58');
INSERT INTO `order_detail` VALUES ('154650419826637', '154650419808903', '157875196366160022', '皮蛋粥', '0.01', '2', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-03 16:30:12', '2019-01-03 16:30:12');
INSERT INTO `order_detail` VALUES ('154650440090256', '154650439020747', '1546421049882', '烤翅', '11.50', '1', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-03 16:33:34', '2019-01-03 16:33:34');
INSERT INTO `order_detail` VALUES ('154650440659594', '154650439020747', '157875196366160022', '皮蛋粥', '0.01', '2', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-03 16:33:34', '2019-01-03 16:33:34');
INSERT INTO `order_detail` VALUES ('154657767725955', '154657767692519', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-04 12:54:38', '2019-01-04 12:54:38');
INSERT INTO `order_detail` VALUES ('154657767738654', '154657767692519', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-04 12:54:38', '2019-01-04 12:54:38');
INSERT INTO `order_detail` VALUES ('154657790447520', '154657790429060', '157875196366160022', '皮蛋粥', '0.01', '10', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-04 12:58:25', '2019-01-04 12:58:25');
INSERT INTO `order_detail` VALUES ('154657790456050', '154657790429060', '164103465734242707', '蜜汁鸡翅', '0.02', '8', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-04 12:58:25', '2019-01-04 12:58:25');

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
INSERT INTO `order_master` VALUES ('1545803562448794', '哈哈', '18563333333', '广东省深圳市', '3534546^&^&%&$^', '5.00', '0', '0', '2018-12-26 13:52:42', '2018-12-26 13:52:42');
INSERT INTO `order_master` VALUES ('1545873939643625', '哈哈', '18563333333', '广东省深圳市', '3534546^&^&%&$^', '0.26', '0', '0', '2018-12-27 09:25:47', '2018-12-27 09:25:47');
INSERT INTO `order_master` VALUES ('1545887330679120', '哈哈', '18563333333', '广东省深圳市', '3534546^&^&%&$^', '0.26', '0', '0', '2018-12-27 13:08:52', '2018-12-27 13:08:52');
INSERT INTO `order_master` VALUES ('1545887862210445', '哈哈', '18563333333', '广东省深圳市', '3534546^&^&%&$^', '0.26', '0', '0', '2018-12-27 13:17:42', '2018-12-27 13:17:42');
INSERT INTO `order_master` VALUES ('1545887905321953', '哈哈', '18563333333', '广东省深圳市', '3534546^&^&%&$^', '0.26', '1', '0', '2018-12-27 13:18:25', '2018-12-30 17:25:48');
INSERT INTO `order_master` VALUES ('1546231321966898', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2018-12-31 12:42:02', '2018-12-31 12:42:02');
INSERT INTO `order_master` VALUES ('1546332469321497', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:47:51', '2019-01-01 16:47:51');
INSERT INTO `order_master` VALUES ('1546332854715952', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:54:15', '2019-01-01 16:54:15');
INSERT INTO `order_master` VALUES ('1546332859730269', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:54:19', '2019-01-01 16:54:19');
INSERT INTO `order_master` VALUES ('1546332866541711', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:54:26', '2019-01-01 16:54:26');
INSERT INTO `order_master` VALUES ('1546333015974815', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:56:56', '2019-01-01 16:56:56');
INSERT INTO `order_master` VALUES ('1546333021095692', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:57:01', '2019-01-01 16:57:01');
INSERT INTO `order_master` VALUES ('1546333038423855', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:57:18', '2019-01-01 16:57:18');
INSERT INTO `order_master` VALUES ('1546333164724958', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 16:59:25', '2019-01-01 16:59:25');
INSERT INTO `order_master` VALUES ('1546333202852321', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:00:02', '2019-01-01 17:00:02');
INSERT INTO `order_master` VALUES ('1546333228594648', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:00:28', '2019-01-01 17:00:28');
INSERT INTO `order_master` VALUES ('1546333250402340', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:00:50', '2019-01-01 17:00:50');
INSERT INTO `order_master` VALUES ('1546333495010896', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:04:55', '2019-01-01 17:04:55');
INSERT INTO `order_master` VALUES ('1546333664552113', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:07:47', '2019-01-01 17:07:47');
INSERT INTO `order_master` VALUES ('1546333797862827', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:10:00', '2019-01-01 17:10:00');
INSERT INTO `order_master` VALUES ('1546333851246519', 'saa', '1934343', 'erdfdf', 'abc', '0.26', '0', '0', '2019-01-01 17:10:53', '2019-01-01 17:10:53');
INSERT INTO `order_master` VALUES ('154650419808903', 'kapok', '123456789012', '北京市', '110101', '11.52', '0', '0', '2019-01-03 16:30:12', '2019-01-03 16:30:12');
INSERT INTO `order_master` VALUES ('154650439020747', 'kapok', '123456789012', '北京市', '110101', '11.52', '0', '1', '2019-01-03 16:33:34', '2019-01-04 10:40:18');
INSERT INTO `order_master` VALUES ('154657767692519', '张三', '18538111791', '北京市', 'abc123', '0.26', '0', '0', '2019-01-04 12:54:38', '2019-01-04 12:54:38');
INSERT INTO `order_master` VALUES ('154657790429060', '张三', '18538111791', '北京市', 'abc123', '0.26', '2', '0', '2019-01-04 12:58:25', '2019-01-04 13:55:55');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热榜', '1', '2017-03-28 16:40:22', '2018-12-26 11:48:02');
INSERT INTO `product_category` VALUES ('2', '好吃的', '2', '2017-03-14 17:38:46', '2018-12-26 11:48:11');
INSERT INTO `product_category` VALUES ('3', '男生最爱', '3', '2019-01-02 16:51:23', '2019-01-02 23:32:39');

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
INSERT INTO `product_info` VALUES ('1546421049882', '烤翅', '11.50', '500', '香嫩的烤鸡翅', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '1', '2019-01-02 17:24:10', '2019-01-04 10:19:53');
INSERT INTO `product_info` VALUES ('157875196366160022', '皮蛋粥', '0.01', '450', '好吃的皮蛋粥', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '0', '1', '2017-03-28 19:39:15', '2019-01-04 13:55:55');
INSERT INTO `product_info` VALUES ('157875227953464068', '慕斯蛋糕', '10.90', '200', '美味爽口', '//fuss10.elemecdn.com/9/93/91994e8456818dfe7b0bd95f10a50jpeg.jpeg', '1', '1', '2017-03-28 19:35:54', '2017-04-21 10:05:57');
INSERT INTO `product_info` VALUES ('164103465734242707', '蜜汁鸡翅', '0.02', '582', '好吃', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '0', '1', '2017-03-30 17:11:56', '2019-01-04 13:55:55');

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
INSERT INTO `user_info` VALUES ('1111', '', '', 'abc', '0', '2018-12-30 12:24:57', '2018-12-30 13:02:45');
INSERT INTO `user_info` VALUES ('2222', '', '', 'xyz', '1', '2018-12-30 12:25:10', '2018-12-30 12:58:25');
