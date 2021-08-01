/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : geektime_shop

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 01/08/2021 14:10:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(64) DEFAULT NULL COMMENT '商品名',
  `sku_list` varchar(128) DEFAULT NULL COMMENT '商品sku列表',
  `item_pic` varchar(1024) DEFAULT NULL COMMENT '商品大图',
  `item_images` varchar(2096) DEFAULT NULL COMMENT '商品详情图',
  `item_price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `item_status` int(11) DEFAULT NULL COMMENT '商品状态：上下架,,,',
  `item_sales_num` int(11) DEFAULT NULL COMMENT '商品销量',
  `item_left_num` int(11) DEFAULT NULL COMMENT '剩余库存',
  `item_type` varchar(512) DEFAULT NULL COMMENT '商品类别',
  `item_descript_info` varchar(2048) DEFAULT NULL COMMENT '商品文字详情信息',
  `del_flag` tinyint(2) DEFAULT NULL COMMENT '删除状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(11) NOT NULL,
  `user_id` bigint(11) DEFAULT NULL COMMENT '下单用户id',
  `commodity_id` bigint(11) DEFAULT NULL COMMENT '下单商品id',
  `receive_addr` varchar(128) DEFAULT NULL COMMENT '收件地址',
  `receive_name` varchar(32) DEFAULT NULL COMMENT '收件姓名',
  `receive_phone_num` varchar(16) DEFAULT NULL COMMENT '收件电话',
  `order_status` int(8) DEFAULT NULL COMMENT '订单状态：枚举',
  `express_num` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `express_company` varchar(32) DEFAULT NULL COMMENT '快递公司名',
  `commodity_price` decimal(10,2) DEFAULT NULL COMMENT '产品价格',
  `coupon_info` varchar(512) DEFAULT NULL COMMENT '优惠信息',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '实际支付价格',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `refund_status` tinyint(2) DEFAULT NULL COMMENT '退款状态',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_reason` varchar(32) DEFAULT NULL COMMENT '退款理由',
  `order_comment` varchar(2048) DEFAULT NULL COMMENT '订单评论',
  `order_comment_star` varchar(8) DEFAULT NULL COMMENT '评论星级',
  `del_flag` tinyint(2) DEFAULT NULL COMMENT '删除状态',
  `cancel_flag` tinyint(2) DEFAULT NULL COMMENT '取消状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `refund_num` int(11) DEFAULT NULL COMMENT '退款数量',
  `express_info` varchar(512) DEFAULT NULL COMMENT '物流信息',
  `commodity_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单号',
  `is_leaf_order` tinyint(2) DEFAULT NULL COMMENT '是否子订单',
  `parent_order_sn` varchar(64) DEFAULT NULL COMMENT '父订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `phone_number` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `acount_name` varchar(16) DEFAULT NULL COMMENT '登陆账号',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  `id_card` varchar(32) DEFAULT NULL COMMENT '实名身份证',
  `turth_name` varchar(8) DEFAULT NULL COMMENT '实名姓名',
  `del_flag` tinyint(2) DEFAULT '0' COMMENT '删除状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
