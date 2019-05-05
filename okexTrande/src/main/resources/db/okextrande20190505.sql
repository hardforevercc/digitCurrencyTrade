/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 100121
Source Host           : localhost:3306
Source Database       : okextrande

Target Server Type    : MYSQL
Target Server Version : 100121
File Encoding         : 65001

Date: 2019-05-05 19:33:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for okex_grid_config
-- ----------------------------
DROP TABLE IF EXISTS `okex_grid_config`;
CREATE TABLE `okex_grid_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) NOT NULL COMMENT '币种',
  `x` double(10,2) DEFAULT NULL COMMENT '下跌百分比',
  `y` double(10,2) DEFAULT NULL COMMENT '下跌最大百分比',
  `n` int(10) DEFAULT NULL COMMENT '资金段数',
  `totalAmt` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for okex_grid_plan
-- ----------------------------
DROP TABLE IF EXISTS `okex_grid_plan`;
CREATE TABLE `okex_grid_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) DEFAULT NULL COMMENT '币种',
  `buyId` varchar(16) DEFAULT NULL COMMENT '买入ID',
  `sellId` varchar(16) DEFAULT NULL COMMENT '卖出ID',
  `buyPrice` decimal(10,4) DEFAULT NULL COMMENT '预期买入价格',
  `sellPrice` decimal(10,4) DEFAULT NULL COMMENT '预期卖出价格',
  `amount` int(11) DEFAULT NULL COMMENT '预期买入数量',
  `buyAmt` decimal(10,4) DEFAULT NULL COMMENT '买入总金额',
  `actBuyPrice` decimal(10,4) DEFAULT NULL COMMENT '实际买入价格',
  `actbuyAmount` decimal(10,4) DEFAULT NULL COMMENT '实际买入量',
  `actSellPrice` decimal(10,4) DEFAULT NULL COMMENT '实际卖出价格',
  `actSellAmount` decimal(10,4) DEFAULT NULL COMMENT '实际卖出数量',
  `buySts` varchar(10) DEFAULT NULL COMMENT '买入订单状态("-2":失败,"-1":撤单成功,"0":等待成交 ,"1":部分成交, "2":完全成交,"3":下单中,"4":撤单中 ）',
  `sellSts` varchar(10) DEFAULT NULL COMMENT '卖出状态: 0 初始 \r\n1 交易成功\r\n2 交易失败，已撤单\r\n3 挂单',
  `create_date` datetime DEFAULT '0000-00-00 00:00:00',
  `update_date` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for okex_trade_record
-- ----------------------------
DROP TABLE IF EXISTS `okex_trade_record`;
CREATE TABLE `okex_trade_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `curprice` decimal(10,4) DEFAULT NULL COMMENT '当前价格',
  `curamount` decimal(10,6) DEFAULT NULL COMMENT '当前数量',
  `amt` decimal(10,6) DEFAULT NULL COMMENT '总金额',
  `type` varchar(20) DEFAULT NULL COMMENT '类型:sell 卖出 buy买入',
  `isOk` varchar(2) DEFAULT NULL COMMENT '交易是否成功 Y成功 N失败',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

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
-- Table structure for price_record
-- ----------------------------
DROP TABLE IF EXISTS `price_record`;
CREATE TABLE `price_record` (
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
