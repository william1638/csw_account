-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 120.55.113.192    Database: xnaccount
-- ------------------------------------------------------
-- Server version	5.5.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tstd_account`
--

DROP TABLE IF EXISTS `tstd_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_account` (
  `user_id` varchar(32) NOT NULL,
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `account_number` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `amount` bigint(32) NOT NULL,
  `frozen_amount` bigint(32) NOT NULL,
  `currency` varchar(8) NOT NULL,
  `md5` varchar(32) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_account_frozen_jour`
--

DROP TABLE IF EXISTS `tstd_account_frozen_jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_account_frozen_jour` (
  `afj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(32) NOT NULL,
  `pre_amount` bigint(32) NOT NULL,
  `post_amount` bigint(32) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`afj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_account_jour`
--

DROP TABLE IF EXISTS `tstd_account_jour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_account_jour` (
  `aj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `status` char(1) NOT NULL,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(32) NOT NULL,
  `pre_amount` bigint(32) NOT NULL,
  `post_amount` bigint(32) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `work_date` varchar(8) NOT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`aj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_bank`
--

DROP TABLE IF EXISTS `tstd_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `bank_no` varchar(8) NOT NULL,
  `bank_name` varchar(64) NOT NULL,
  `bank_type` varchar(4) DEFAULT NULL,
  `quick_type` char(1) DEFAULT NULL,
  `is_enable` char(1) DEFAULT NULL,
  `channel_no` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_bankcard`
--

DROP TABLE IF EXISTS `tstd_bankcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_bankcard` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `type` char(1) NOT NULL COMMENT '类型(对公对私)',
  `bank_code` varchar(8) DEFAULT NULL COMMENT '银行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bankcard_no` varchar(64) NOT NULL COMMENT '银行卡编号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `status` char(1) NOT NULL COMMENT '状态',
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_channel`
--

DROP TABLE IF EXISTS `tstd_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_channel` (
  `channel_no` varchar(4) NOT NULL,
  `channel_name` varchar(16) NOT NULL,
  `channel_status` char(1) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`channel_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_company`
--

DROP TABLE IF EXISTS `tstd_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_company` (
  `company_id` varchar(32) NOT NULL COMMENT '编号',
  `company_name` varchar(64) NOT NULL COMMENT '公司名字',
  `licence_no` varchar(255) DEFAULT NULL COMMENT '工商营业执照号',
  `id_kind` char(1) DEFAULT NULL COMMENT '法人证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '法人证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '法人真实姓名',
  `capital` bigint(32) DEFAULT NULL COMMENT '注册资金',
  `province` varchar(16) NOT NULL COMMENT '省',
  `city` varchar(16) NOT NULL COMMENT '市',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `gsyyzz_picture` varchar(255) DEFAULT NULL COMMENT '工商营业执照',
  `zzjgdmz_picture` varchar(255) DEFAULT NULL COMMENT '组织机构代码证',
  `swdjz_picture` varchar(255) DEFAULT NULL COMMENT '税务登记证',
  `dzz_picture` varchar(255) DEFAULT NULL COMMENT '电子章',
  `sqgh_picture` varchar(255) DEFAULT NULL COMMENT '申请公函',
  `fr_picture` varchar(255) DEFAULT NULL COMMENT '法人身份证照片',
  `other_picture` varchar(255) DEFAULT NULL COMMENT '其他照片',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_datetime` datetime NOT NULL COMMENT '申请时间',
  `kyc_user` varchar(32) DEFAULT NULL COMMENT 'KYC审批人',
  `kyc_datetime` datetime DEFAULT NULL COMMENT 'KYC时间',
  `kyc_note` varchar(255) DEFAULT NULL COMMENT 'KYC结果说明',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_cqorder`
--

DROP TABLE IF EXISTS `tstd_cqorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_cqorder` (
  `cq_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `channel` varchar(2) NOT NULL,
  `amount` bigint(32) NOT NULL,
  `bank_code` varchar(8) DEFAULT NULL,
  `bankcard_no` varchar(32) DEFAULT NULL,
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `pay_user` varchar(32) DEFAULT NULL,
  `pay_datetime` datetime DEFAULT NULL,
  `pay_no` varchar(32) DEFAULT NULL,
  `pay_fee` bigint(20) DEFAULT NULL,
  `work_date` varchar(8) DEFAULT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`cq_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_hlorder`
--

DROP TABLE IF EXISTS `tstd_hlorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_hlorder` (
  `hl_no` varchar(32) NOT NULL,
  `type` char(1) NOT NULL COMMENT '类型',
  `status` char(1) NOT NULL COMMENT '状态',
  `direction` char(1) NOT NULL COMMENT '方向',
  `amount` bigint(32) NOT NULL COMMENT '金额',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_note` varchar(255) NOT NULL COMMENT '申请说明',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  PRIMARY KEY (`hl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user`
--

DROP TABLE IF EXISTS `tstd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL,
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `login_pwd` varchar(32) NOT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) NOT NULL COMMENT '登陆密码强度',
  `user_kind` varchar(4) NOT NULL COMMENT '身份标识',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `serve_list` varchar(32) DEFAULT NULL COMMENT '拥有的服务list',
  `quote_list` varchar(32) DEFAULT NULL COMMENT '拥有的报价list',
  `level` varchar(32) DEFAULT NULL COMMENT '用户等级',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_company`
--

DROP TABLE IF EXISTS `tstd_user_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_company` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `company_id` varchar(32) NOT NULL COMMENT '公司编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_ext`
--

DROP TABLE IF EXISTS `tstd_user_ext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_ext` (
  `user_id` varchar(32) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `comefrom` varchar(64) DEFAULT NULL,
  `birthday` varchar(8) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `occupation` varchar(8) DEFAULT NULL,
  `education` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_identify_log`
--

DROP TABLE IF EXISTS `tstd_user_identify_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_identify_log` (
  `user_id` varchar(32) NOT NULL,
  `id_kind` varchar(1) NOT NULL,
  `id_no` varchar(32) NOT NULL,
  `real_name` varchar(16) NOT NULL,
  `error_code` varchar(1) NOT NULL,
  `error_info` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_lock`
--

DROP TABLE IF EXISTS `tstd_user_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_lock` (
  `user_id` varchar(32) NOT NULL,
  `lock_direction` char(1) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_login_log`
--

DROP TABLE IF EXISTS `tstd_user_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_login_log` (
  `user_id` varchar(32) NOT NULL,
  `login_datetime` datetime NOT NULL,
  `login_ip` varchar(64) NOT NULL,
  `is_success` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_user_picture`
--

DROP TABLE IF EXISTS `tstd_user_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `id_kind` varchar(1) NOT NULL,
  `id_no` varchar(32) NOT NULL,
  `real_name` varchar(16) NOT NULL,
  `id_pic1` varchar(255) NOT NULL,
  `id_pic2` varchar(255) NOT NULL,
  `id_user_pic` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `verify_user` varchar(32) DEFAULT NULL,
  `verify_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_xnborder`
--

DROP TABLE IF EXISTS `tstd_xnborder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_xnborder` (
  `xnb_no` varchar(20) NOT NULL,
  `status` char(1) NOT NULL,
  `type` char(1) NOT NULL,
  `score` bigint(32) NOT NULL,
  `amount` bigint(32) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`xnb_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_ytorder`
--

DROP TABLE IF EXISTS `tstd_ytorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_ytorder` (
  `yt_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `biz_type` varchar(2) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`yt_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tstd_zzorder`
--

DROP TABLE IF EXISTS `tstd_zzorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tstd_zzorder` (
  `zz_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `amount` bigint(32) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `opposite_system` char(2) NOT NULL,
  `opposite_account` varchar(32) DEFAULT NULL,
  `remark` varchar(255) NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`zz_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-19 19:16:56
