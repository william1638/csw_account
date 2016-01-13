SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `tcms_article`;
CREATE TABLE `tcms_article` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `parent_code` varchar(32) NOT NULL COMMENT '父导航',
  `art_url` varchar(64) NOT NULL,
  `status` varchar(32) NOT NULL COMMENT '状态',
  `order_no` varchar(8) NOT NULL,
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tcms_navigation`;
CREATE TABLE `tcms_navigation` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `nav_url` varchar(256) NOT NULL COMMENT '名称',
  `type` varchar(4) NOT NULL COMMENT '类型(主导航，子导航，顶部导航栏)',
  `parent_code` varchar(32) NOT NULL COMMENT '父编号',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `order_no` varchar(8) NOT NULL,
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_account`;
CREATE TABLE `tli_account` (
  `account_number` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `frozen_amount` bigint(20) NOT NULL,
  `currency` varchar(8) NOT NULL,
  `md5` varchar(32) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_account_frozen_jour`;
CREATE TABLE `tli_account_frozen_jour` (
  `afj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(20) NOT NULL,
  `pre_amount` bigint(20) NOT NULL,
  `post_amount` bigint(20) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`afj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_account_jour`;
CREATE TABLE `tli_account_jour` (
  `aj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `status` char(1) NOT NULL,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(20) NOT NULL,
  `pre_amount` bigint(20) NOT NULL,
  `post_amount` bigint(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `work_date` varchar(8) NOT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`aj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_bank`;
CREATE TABLE `tli_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `bank_no` varchar(8) NOT NULL,
  `bank_name` varchar(64) NOT NULL,
  `bank_type` varchar(4) DEFAULT NULL,
  `quick_type` char(1) DEFAULT NULL,
  `is_enable` char(1) DEFAULT NULL,
  `channel_no` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_channel`;
CREATE TABLE `tli_channel` (
  `channel_no` varchar(4) NOT NULL,
  `channel_name` varchar(16) NOT NULL,
  `channel_status` char(1) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `merchant_id` varchar(8) DEFAULT NULL,
  `sign_type` varchar(8) DEFAULT NULL,
  `sign_key` varchar(8) DEFAULT NULL,
  `cer_path` varchar(8) DEFAULT NULL,
  `poundage_type` varchar(8) DEFAULT NULL,
  `channel_version` varchar(8) DEFAULT NULL,
  `business_web_gateway` varchar(8) DEFAULT NULL,
  `business_wap_gateway` varchar(8) DEFAULT NULL,
  `business_file_gateway` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`channel_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_cqorder`;
CREATE TABLE `tli_cqorder` (
  `cq_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `channel` varchar(2) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `bank_code` varchar(12) DEFAULT NULL,
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

DROP TABLE IF EXISTS  `tli_hlorder`;
CREATE TABLE `tli_hlorder` (
  `hl_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `apply_user` varchar(32) NOT NULL,
  `apply_note` varchar(255) NOT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`hl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_xnborder`;
CREATE TABLE `tli_xnborder` (
  `xnb_no` varchar(20) NOT NULL,
  `status` char(1) NOT NULL,
  `type` char(1) NOT NULL,
  `score` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`xnb_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tli_ytorder`;
CREATE TABLE `tli_ytorder` (
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

DROP TABLE IF EXISTS  `tli_zzorder`;
CREATE TABLE `tli_zzorder` (
  `zz_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `opposite_system` char(2) NOT NULL,
  `opposite_account` varchar(32) DEFAULT NULL,
  `remark` varchar(255) NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`zz_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_bankcard`;
CREATE TABLE `tpz_bankcard` (
  `user_id` varchar(32) NOT NULL,
  `bank_code` varchar(8) DEFAULT NULL,
  `bank_name` varchar(32) DEFAULT NULL,
  `bankcard_no` varchar(64) DEFAULT NULL,
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `status` char(1) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_datetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_sms_captcha`;
CREATE TABLE `tpz_sms_captcha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) DEFAULT NULL,
  `biz_type` varchar(2) DEFAULT NULL,
  `sms_captcha` varchar(8) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL,
  `sent_datetime` timestamp NULL DEFAULT NULL,
  `invalid_datetime` timestamp NULL DEFAULT NULL,
  `check_datetime` timestamp NULL DEFAULT NULL,
  `check_times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3835 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user`;
CREATE TABLE `tpz_user` (
  `user_id` varchar(32) NOT NULL,
  `mobile` varchar(16) NOT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `login_pwd` varchar(32) NOT NULL,
  `login_pwd_strength` char(1) NOT NULL,
  `user_kind` varchar(4) NOT NULL,
  `user_referee` varchar(32) DEFAULT NULL,
  `id_kind` char(1) DEFAULT NULL,
  `id_no` varchar(32) DEFAULT NULL,
  `trade_pwd` varchar(32) DEFAULT NULL,
  `trade_pwd_strength` char(1) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_ext`;
CREATE TABLE `tpz_user_ext` (
  `user_id` varchar(32) NOT NULL,
  `photo` varchar(256) DEFAULT NULL,
  `comefrom` varchar(64) DEFAULT NULL,
  `birthday` varchar(8) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `occupation` varchar(8) DEFAULT NULL,
  `education` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_identify_log`;
CREATE TABLE `tpz_user_identify_log` (
  `user_id` varchar(32) DEFAULT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `id_kind` varchar(1) DEFAULT NULL,
  `id_no` varchar(32) DEFAULT NULL,
  `error_code` varchar(1) DEFAULT NULL,
  `error_info` varchar(256) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_lock`;
CREATE TABLE `tpz_user_lock` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL,
  `lock_direction` char(1) NOT NULL,
  `remark` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_login_log`;
CREATE TABLE `tpz_user_login_log` (
  `user_id` varchar(32) NOT NULL,
  `login_datetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `login_ip` varchar(64) NOT NULL,
  `is_success` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_picture`;
CREATE TABLE `tpz_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `id_pic1` varchar(255) DEFAULT NULL,
  `id_pic2` varchar(255) DEFAULT NULL,
  `id_user_pic` varchar(255) DEFAULT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `id_kind` varchar(1) DEFAULT NULL,
  `id_no` varchar(32) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL,
  `verify_datetime` timestamp NULL DEFAULT NULL,
  `verify_status` varchar(1) DEFAULT NULL,
  `verify_user` varchar(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL,
  `dkey` varchar(32) DEFAULT NULL,
  `dvalue` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

