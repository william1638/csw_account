/*
 Navicat MySQL Data Transfer

 Source Server         : 148
 Source Server Version : 50545
 Source Host           : 121.43.101.148
 Source Database       : std_account

 Target Server Version : 50545
 File Encoding         : utf-8

 Date: 11/10/2016 20:58:27 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tstd_account`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_account`;
CREATE TABLE `tstd_account` (
  `system_code` varchar(32) NOT NULL COMMENT '系统编号',
  `account_name` varchar(32) NOT NULL COMMENT '户名',
  `account_number` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` bigint(32) DEFAULT NULL COMMENT '余额',
  `frozen_amount` bigint(32) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_account_jour`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_account_jour`;
CREATE TABLE `tstd_account_jour` (
  `system_code` varchar(32) NOT NULL COMMENT '系统编号',
  `account_name` varchar(32) NOT NULL COMMENT '户名',
  `account_number` varchar(32) NOT NULL DEFAULT NULL COMMENT '账号',
  `order` varchar(32) DEFAULT NULL COMMENT '流水编号',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付类型',
  `pay_order` varchar(32) DEFAULT NULL COMMENT '渠道单号',
  `biz_type` varchar(4) DEFAULT NULL COMMENT '业务类型',
  `trans_amount` bigint(32) DEFAULT NULL COMMENT '变动金额',
  `pre_amount` bigint(32) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` bigint(32) DEFAULT NULL COMMENT '变动后金额',
  `trans_datetime` datetime DEFAULT NULL COMMENT '金额变动时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（刚生成待回调，无需对账，已回调待对账，对账通过，对账不通过待调账，已调账）',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_channel_bank`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_channel_bank`;
CREATE TABLE `tstd_channel_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `paybank` varchar(32) DEFAULT NULL COMMENT '渠道给银行的代号',
  `max_order` int(11) DEFAULT NULL COMMENT '笔数限制',
  `order_amount` bigint(20) DEFAULT NULL COMMENT '单笔限额',
  `day_amount` bigint(20) DEFAULT NULL COMMENT '每日限额',
  `month_amount` bigint(20) DEFAULT NULL COMMENT '每月限额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_company_channel`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_company_channel`;
CREATE TABLE `tstd_company_channel` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `company_name` varchar(32) DEFAULT NULL COMMENT '公司名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `paycompany` varchar(32) DEFAULT NULL COMMENT '渠道给公司的代号',
  `privatekey` varchar(255) DEFAULT NULL COMMENT '秘钥',
  `page_url` varchar(255) DEFAULT NULL COMMENT '界面正确回调地址',
  `error_url` varchar(255) DEFAULT NULL COMMENT '界面错误回调地址',
  `back_url` varchar(255) DEFAULT NULL COMMENT '服务器回调地址',
  `fee` bigint(20) DEFAULT NULL COMMENT '手续费',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `to_system` varchar(4) DEFAULT NULL COMMENT '针对系统',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_bankcard`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_bankcard`;
CREATE TABLE `tstd_bankcard` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `bank_code` varchar(8) DEFAULT NULL COMMENT '银行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bankcard_no` varchar(64) DEFAULT NULL COMMENT '银行卡编号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
