/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 100121
Source Host           : localhost:3306
Source Database       : okextrande

Target Server Type    : MYSQL
Target Server Version : 100121
File Encoding         : 65001

Date: 2018-08-13 21:30:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for okex_trade_record
-- ----------------------------
DROP TABLE IF EXISTS `okex_trade_record`;
CREATE TABLE `okex_trade_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `curprice` decimal(10,2) DEFAULT NULL COMMENT '当前价格',
  `curamount` decimal(10,2) DEFAULT NULL COMMENT '当前数量',
  `amt` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `type` varchar(20) DEFAULT NULL COMMENT '类型:sell 卖出 buy买入',
  `isOk` varchar(2) DEFAULT NULL COMMENT '交易是否成功 Y成功 N失败',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of okex_trade_record
-- ----------------------------

-- ----------------------------
-- Table structure for okex_trand_info
-- ----------------------------
DROP TABLE IF EXISTS `okex_trand_info`;
CREATE TABLE `okex_trand_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `traddes_name` varchar(10) NOT NULL COMMENT '交易对名称',
  `timestamp` varchar(20) DEFAULT NULL COMMENT '时间戳',
  `ticker_buy` decimal(16,4) DEFAULT NULL COMMENT '买一价',
  `ticker_high` decimal(16,4) DEFAULT NULL COMMENT '最高成交价',
  `ticker_last` decimal(16,4) DEFAULT NULL COMMENT '最近成交价',
  `ticker_low` decimal(16,4) DEFAULT NULL COMMENT '最低成交价',
  `ticker_sell` decimal(16,4) DEFAULT NULL COMMENT '卖一价',
  `ticker_vol` decimal(16,6) DEFAULT NULL COMMENT '成交量(最近24小时)',
  `trade_sell_max_price` decimal(16,4) DEFAULT NULL COMMENT '最大卖出交易价格(近200笔）',
  `trade_sell_max_amount` decimal(16,4) DEFAULT NULL COMMENT '最大卖出交易量(近200笔）',
  `trade_sell_min_price` decimal(16,4) DEFAULT NULL COMMENT '最小卖出交易价格(近200笔）',
  `trade_sell_min_amount` decimal(16,4) DEFAULT NULL COMMENT '最小卖出交易量(近200笔）',
  `trade_sell_avg_price` decimal(16,4) DEFAULT NULL COMMENT '平均卖出价格(近200笔）',
  `trade_sell_avg_amount` decimal(16,4) DEFAULT NULL COMMENT '平均卖出交易量(近200笔）',
  `trade_buy_max_price` decimal(16,4) DEFAULT NULL COMMENT '最大买入价格',
  `trade_buy_max_amount` decimal(16,4) DEFAULT NULL COMMENT '最大买入交易量',
  `trade_buy_min_price` decimal(16,4) DEFAULT NULL COMMENT '最小买入价格',
  `trade_buy_min_amount` decimal(16,4) DEFAULT NULL COMMENT '最小买入量',
  `trade_buy_avg_price` decimal(16,4) DEFAULT NULL COMMENT '平均买入价格',
  `trade_buy_avg_amount` decimal(16,4) DEFAULT NULL COMMENT '平均买入量',
  `trad_sell_nums` int(10) DEFAULT NULL COMMENT '获取卖出数量',
  `trad_buy_nums` int(10) DEFAULT NULL COMMENT '获取买入数量',
  `dep_ask_max` decimal(16,4) DEFAULT NULL COMMENT '最大卖方深度',
  `dep_ask_min` decimal(16,4) DEFAULT NULL COMMENT '最小卖方深度',
  `dep_ask_avg` decimal(16,4) DEFAULT NULL COMMENT '平均卖方深度',
  `dep_bid_max` decimal(16,4) DEFAULT NULL COMMENT '最大买方深度',
  `dep_bid_min` decimal(16,4) DEFAULT NULL COMMENT '最小买方深度',
  `dep_bid_avg` decimal(16,4) DEFAULT NULL COMMENT '平均买方深度',
  `dep_ask_nums` int(10) DEFAULT NULL COMMENT '获取卖方深度条数',
  `dep_bid_nums` int(10) DEFAULT NULL COMMENT '获取卖方深度条数',
  `usdt_btc` decimal(16,4) DEFAULT NULL COMMENT 'BTC价格',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of okex_trand_info
-- ----------------------------
